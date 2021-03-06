package com.turkcell.bipai.helloworld.service;

import java.util.logging.Logger;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.turkcell.bipai.helloworld.api.tes.request.TesMultiUserDifferentMessageRequest;
import com.turkcell.bipai.helloworld.api.tes.request.TesMultiUserSameMessageRequest;
import com.turkcell.bipai.helloworld.api.tes.request.TesSingleUserRequest;
import com.turkcell.bipai.helloworld.api.tes.response.TesMultiDifferentMessageResponse;
import com.turkcell.bipai.helloworld.api.tes.response.TesMultiUserSameMessageResponse;
import com.turkcell.bipai.helloworld.api.tes.response.TesSingleUserResponse;
import com.turkcell.bipai.helloworld.util.AppConstant;
import com.turkcell.bipai.helloworld.util.BasicAuthRestTemplate;

public class Service {

	private static final Logger logger = Logger.getLogger(Service.class.getName());	
	
	/**
	 * TES servisinin sendmsgserv metodunu kullanarak, request nesnesi içinde belirtilen bir kullanıcıya veya 
	 * servisin tüm takipçilerine mesaj/mesaj dizisi gönderir. Tek kullanıcıya mesaj gönderilmek isteniyorsa Receiver nesnesinin
	 * type değişkeni "0" (Karıştırılmış-Opaque numara) veya "2" (MSISDN - gerçek numara) olmalı, tüm takipçilere gönderilmek 
	 * isteniyorsa "1" olmalıdır. Varsayılan type değişkeni "0" dır.
	 * 
	 * @param request JSON olarak gönderilecek olan TesSingleUserRequest objesidir.
	 * @see <a href="http://www.bip.ai/documentations/tek-kullaniciya-mesaj-gonderimi/">http://www.bip.ai/documentations/tek-kullaniciya-mesaj-gonderimi/</a>
	 */
	protected void send(TesSingleUserRequest request) {
		
		RestTemplate	restTemplate			=	new BasicAuthRestTemplate(AppConstant.USER, AppConstant.PASS);
		logger.info("Tek kullanıcıya mesaj gönderiliyor..");
		logger.info("Gönderilen JSON: " + new Gson().toJson(request));
		
		try{
			TesSingleUserResponse response		=	restTemplate.postForObject(AppConstant.SINGLE_USER, request, TesSingleUserResponse.class);
			if(response.getResultcode() == 0)
				logger.info("Mesaj başarıyla gönderildi!");
			else {
				logger.info("Hata kodu aldınız.");
				logger.info("resultcode: " + response.getResultcode());
				logger.info("Daha fazla bilgi için: http://www.bip.ai/documentations/hata-kodlari/");
			}
		}
		catch(HttpClientErrorException e) {
			logger.info("Doğrulama Hata kodu aldınız.");
			logger.info("Hata: " + e.getResponseBodyAsString());
			logger.info("Daha fazla bilgi için: http://www.bip.ai/documentations/content-type-ozelindeki-kontroller/");
		}
		
	}
	
	/**
	 * TES servisinin sendmsgservlist metodunu kullanarak, request nesnesi içinde belirtilen birden fazla kullanıcıya aynı mesaj/mesaj dizisini gönderir. 
	 * 
	 * @param request JSON olarak gönderilecek olan TesMultiUserListRequest objesidir.
	 * @see <a href="http://www.bip.ai/documentations/birden-fazla-kullaniciya-farkli-mesaj-gonderimi/">http://www.bip.ai/documentations/birden-fazla-kullaniciya-ayni-mesaj-gonderimi/</a>
	 */
	protected void send(TesMultiUserSameMessageRequest request) {
		
		RestTemplate	restTemplate						=	new BasicAuthRestTemplate(AppConstant.USER, AppConstant.PASS);
		logger.info("Çok kullanıcıya aynı mesaj gönderiliyor..");
		logger.info("Gönderilen JSON: " + new Gson().toJson(request));
		
		try{
			TesMultiUserSameMessageResponse response		=	restTemplate.postForObject(AppConstant.MULTI_USER_LIST, request, TesMultiUserSameMessageResponse.class);
			// TES raporlama fonksiyonu geliştirilmekte olup şu anda null değeri dönmektedir..
			// logger.info("Result Code: " + response.getResultcode() + " Report: " + response.getReport());
			if(response.getResultcode() == 0)
				logger.info("Mesaj başarıyla gönderildi!");
			else {
				logger.info("Hata kodu aldınız.");
				logger.info("resultcode: " + response.getResultcode());
				logger.info("Daha fazla bilgi için: http://www.bip.ai/documentations/hata-kodlari/");
			}
		}
		catch(HttpClientErrorException e) {
			logger.info("Doğrulama Hata kodu aldınız.");
			logger.info("Hata: " + e.getResponseBodyAsString());
			logger.info("Daha fazla bilgi için: http://www.bip.ai/documentations/content-type-ozelindeki-kontroller/");
		}
		
	}
	
	/**
	 * TES servisinin sendmultiusermulticontent metodunu kullanarak, request nesnesi içinde belirtilen birden fazla kullanıcıya farklı mesaj/mesaj dizisi gönderir. 
	 * 
	 * @param request JSON olarak gönderilecek olan TesMultiUserRequest objesidir.
	 * @see <a href="http://www.bip.ai/documentations/cok-kullaniciya-mesaj-gonderimi/">http://www.bip.ai/documentations/cok-kullaniciya-mesaj-gonderimi/</a>
	 */
	protected void send(TesMultiUserDifferentMessageRequest request) {
		
		RestTemplate	restTemplate						=	new BasicAuthRestTemplate(AppConstant.USER, AppConstant.PASS);
		logger.info("Cok kullanıcıya farklı mesaj gönderiliyor..");
		logger.info("Gönderilen JSON: " + new Gson().toJson(request));
		
		try{
			TesMultiDifferentMessageResponse response		=	restTemplate.postForObject(AppConstant.MULTI_USER, request, TesMultiDifferentMessageResponse.class);
			// TES raporlama fonksiyonu geliştirilmekte olup şu anda null değeri dönmektedir..
			// logger.info("Result Code: " + response.getResultcode() + " Report: " + response.getReport());			
			if(response.getResultcode() == 0)
				logger.info("Mesaj başarıyla gönderildi!");
			else {
				logger.info("Hata kodu aldınız.");
				logger.info("resultcode: " + response.getResultcode());
				logger.info("Daha fazla bilgi için: http://www.bip.ai/documentations/hata-kodlari/");
			}
		}
		catch(HttpClientErrorException e) {
			logger.info("Doğrulama Hata kodu aldınız.");
			logger.info("Hata: " + e.getResponseBodyAsString());
			logger.info("Daha fazla bilgi için: http://www.bip.ai/documentations/content-type-ozelindeki-kontroller/");
		}
		
	}
}
