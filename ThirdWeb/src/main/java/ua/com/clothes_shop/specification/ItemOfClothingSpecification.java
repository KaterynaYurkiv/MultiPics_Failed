package ua.com.clothes_shop.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.clothes_shop.dto.filter.ItemOfClothingFilter;
import ua.com.clothes_shop.entity.ItemOfClothing;

public class ItemOfClothingSpecification implements Specification<ItemOfClothing>{
	
    private final ItemOfClothingFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	private static final Pattern REG = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");
	
	private static final  Pattern REGS = Pattern.compile("^[0-9]+$");
	
	public ItemOfClothingSpecification(ItemOfClothingFilter filter) {
		this.filter = filter;
		if(REG.matcher(filter.getMax()).matches()){
			filter.setMaxValue(new BigDecimal(filter.getMax().replace(',', '.')));
		}
		if(REG.matcher(filter.getMin()).matches()){
			filter.setMinValue(new BigDecimal(filter.getMin().replace(',', '.')));
		}
		if(REGS.matcher(filter.getMaxM()).matches()){
			filter.setMaxMValue(new Integer(filter.getMaxM()));
		}
		if(REG.matcher(filter.getMinM()).matches()){
			filter.setMinMValue(new Integer(filter.getMinM()));
		}
	}
	
	
	private void filterByItemName(Root<ItemOfClothing> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getItemNameId().isEmpty()){
			predicates.add(root.get("itemName").in(filter.getItemNameId()));
		}
	}
	
	private void filterByBrand(Root<ItemOfClothing> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getBrandId().isEmpty()){
			predicates.add(root.get("brand").in(filter.getBrandId()));
		}
	}
	
	private void filterByTargetAudience(Root<ItemOfClothing> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getTargetAudienceId().isEmpty()){
			predicates.add(root.get("targetAudience").in(filter.getTargetAudienceId()));
		}
	}
	
	private void filterByTypeOfClothing(Root<ItemOfClothing> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getTypeOfClothingId().isEmpty()){
			predicates.add(root.get("typeOfClothing").in(filter.getTypeOfClothingId()));
		}
	}
	
	private void filterByColor(Root<ItemOfClothing> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getColorId().isEmpty()){
			predicates.add(root.get("color").in(filter.getColorId()));
		}
	}
	
	private void filterBySize(Root<ItemOfClothing> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSizeId().isEmpty()){
			predicates.add(root.get("size").in(filter.getSizeId()));
		}
	}
	
	private void filterByPrice(Root<ItemOfClothing> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxValue()!=null){
			predicates.add(cb.le(root.get("price"), filter.getMaxValue()));
		}
		if(filter.getMinValue()!=null){
			predicates.add(cb.ge(root.get("price"), filter.getMinValue()));
		}
	}
	
	private void filterByMarking(Root<ItemOfClothing> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxMValue()!=null){
			predicates.add(cb.le(root.get("marking"), filter.getMaxMValue()));
		}
		if(filter.getMinMValue()!=null){
			predicates.add(cb.ge(root.get("marking"), filter.getMinMValue()));
		}
	}
	
	private void fetch(Root<ItemOfClothing> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			query.distinct(true); //використовують коли треба л≥ст вит€гнути ≥ щоб значенн€ в ньому не дублювались
			root.fetch("itemName");  //			root.fetch("itemName", JoinType.LEFT);
			root.fetch("brand");  //			root.fetch("brand");
			root.fetch("targetAudience");
			root.fetch("typeOfClothing");
			root.fetch("color");
			root.fetch("size");
			root.fetch("images");
			System.out.println("Hello from fetch");
		}
	}
	

	@Override
	public Predicate toPredicate(Root<ItemOfClothing> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		filterByPrice(root, query, cb);
		filterByMarking(root, query, cb);
		filterByItemName(root, query, cb);
		filterByBrand(root, query, cb);
		filterByTargetAudience(root, query, cb);
		filterByTypeOfClothing(root, query, cb);
		filterByColor(root, query, cb);
		filterBySize(root, query, cb);
		if(predicates.isEmpty()) return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}

}
