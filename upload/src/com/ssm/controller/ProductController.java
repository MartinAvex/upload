package com.ssm.controller;



import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ssm.utils.POIUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ssm.entity.Product;
import com.ssm.service.ProductService;

import javax.xml.rpc.ServiceException;


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
    public String fileUpload(MultipartFile file,Product product, ModelMap map) throws IOException, ServiceException {

		try {

			List<Product> productList = POIUtils.readExcel(file, new POIUtils.ExcelRowsHandler<Product>() {

				@Override
				public Product execute(int lineNum, String[] rows) {
					int i = 0;
					String name = rows[i++]; // 名称
					String gene = rows[i++]; // 基因序列


					Product product = new Product();
					product.setName(name);
					if (StringUtils.isNotEmpty(gene) && gene.length() >= 11) {
						String headStr = gene.substring(0, 10);
						String endStr = gene.substring(11);
						String tenCharacter = String.valueOf(gene.charAt(10));
						product.setHead(headStr);
						product.setTen(tenCharacter);
						product.setEnd(endStr);
					}
					product.setGene(gene);
					return product;
				}

			});

			for (Product p : productList) {
				productService.save(p);
			}

		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		/**
         * 保存商品
         */
        return "redirect:/list.do"; 
    }
}
