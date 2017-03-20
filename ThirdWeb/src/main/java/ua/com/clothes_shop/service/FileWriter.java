package ua.com.clothes_shop.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {
	
	enum Folder{
		COLOR, CLOTHES //робимо, щоб створило папку color і не було хаосу
	}
	
	//буде записувати картинку об'єкту знайденому по айді
	boolean write(Folder folder, MultipartFile file, int id);

}
