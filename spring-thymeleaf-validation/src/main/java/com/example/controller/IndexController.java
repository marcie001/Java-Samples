package com.example.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.form.IndexForm;

@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	private final MessageSource messageSource;

	@Autowired
	public IndexController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(@ModelAttribute("form") IndexForm form) {
		return "index";
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public String post(@ModelAttribute("form") @Validated IndexForm form, BindingResult bindingResult, Locale locale) {

		if (bindingResult.hasErrors()) {
			// BindingResult#addError(ObjectError) でグローバルなエラー。
			// ObjectError のコンストラクタの第1引数は自由に決められる名前。第2引数にメッセージ。
			bindingResult.addError(new ObjectError("global", new String[] { "errors.errorsExist" },
					new String[] { messageSource.getMessage("inputError", null, locale),
							messageSource.getMessage("validValue", null, locale) },
					""));

			bindingResult.getGlobalErrors().stream().forEach(e -> logger.info(e.getObjectName()));

			return "index";
		}
		return "redirect:/";
	}

}
