package com.ssm.controller;



import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ssm.entity.Product;
import com.ssm.service.ProductService;



@Controller
public class ProductController {
	//注入ProductService
	@Autowired
	private ProductService productService;
	
	//查询所有用户
	@RequestMapping("/list.do")
	public String listUser( Model model){
		List<Product> list= productService.list();
		model.addAttribute("list",list);
		System.out.println(list);
		return "list";
	}
	
	/**
	 * 保存商品
	 * @param image
	 * @param product
	 * @param map
	 * @return
	 * @throws IOException
	 */
    @RequestMapping("/addProduct.do")
    public String fileUpload(MultipartFile file,Product product, ModelMap map) throws IOException {

        /**
         * 上传图片
         */
    	//图片上传成功后，将图片的地址写到数据库
    	String filePath = "E:\\upload";//保存图片的路径
    	//获取原始图片的拓展名
    	String originalFilename = file.getOriginalFilename();
    	//新的文件名字
    	String newFileName = UUID.randomUUID()+originalFilename;
    	File targetFile = new File(filePath,newFileName); 
    	file.transferTo(targetFile);
        product.setPimage(newFileName);
        
        /**
         * 保存商品
         */
        productService.save(product);
        return "redirect:/list.do"; 
    }
}
