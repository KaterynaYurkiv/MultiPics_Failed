package ua.com.clothes_shop.dto.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemOfClothingFilter {
	
    private String min = "";
	
	private String max = "";
	
	private BigDecimal maxValue;
	
	private BigDecimal minValue;
	
    private String minM = "";
	
	private String maxM = "";
	
	private Integer maxMValue;
	
	private Integer minMValue;
	
	private List<Integer> itemNameId = new ArrayList<>();
	
	private List<Integer> brandId = new ArrayList<>();
	
	private List<Integer> targetAudienceId = new ArrayList<>();
	
	private List<Integer> typeOfClothingId = new ArrayList<>();
	
	private List<Integer> colorId = new ArrayList<>();
	
	private List<Integer> sizeId = new ArrayList<>();

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	public BigDecimal getMinValue() {
		return minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public String getMinM() {
		return minM;
	}

	public void setMinM(String minM) {
		this.minM = minM;
	}

	public String getMaxM() {
		return maxM;
	}

	public void setMaxM(String maxM) {
		this.maxM = maxM;
	}

	public Integer getMaxMValue() {
		return maxMValue;
	}

	public void setMaxMValue(Integer maxMValue) {
		this.maxMValue = maxMValue;
	}

	public Integer getMinMValue() {
		return minMValue;
	}

	public void setMinMValue(Integer minMValue) {
		this.minMValue = minMValue;
	}

	public List<Integer> getItemNameId() {
		return itemNameId;
	}

	public void setItemNameId(List<Integer> itemNameId) {
		this.itemNameId = itemNameId;
	}

	public List<Integer> getBrandId() {
		return brandId;
	}

	public void setBrandId(List<Integer> brandId) {
		this.brandId = brandId;
	}

	public List<Integer> getTargetAudienceId() {
		return targetAudienceId;
	}

	public void setTargetAudienceId(List<Integer> targetAudienceId) {
		this.targetAudienceId = targetAudienceId;
	}

	public List<Integer> getTypeOfClothingId() {
		return typeOfClothingId;
	}

	public void setTypeOfClothingId(List<Integer> typeOfClothingId) {
		this.typeOfClothingId = typeOfClothingId;
	}

	public List<Integer> getColorId() {
		return colorId;
	}

	public void setColorId(List<Integer> colorId) {
		this.colorId = colorId;
	}

	public List<Integer> getSizeId() {
		return sizeId;
	}

	public void setSizeId(List<Integer> sizeId) {
		this.sizeId = sizeId;
	}

}
