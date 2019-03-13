/**
 * Copyright © 2018, Forp Co., LTD
 * <p>
 * All Rights Reserved.
 */
package ocr.demo;

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.ParsingReader;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.ocr.TesseractOCRParser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.ToTextContentHandler;
import org.xml.sax.ContentHandler;

/**
 * OCR工具类(基于Tesseract-Ocr)
 *
 * @author Forp
 * @date 2018年08月30日 下午13:25:00
 */
public class Ocr
{
	
	/**
	 * Configuration
	 */
	private static TesseractOCRConfig config = new TesseractOCRConfig();

	public Ocr()
	{

		// xml配置参数
		config.setTesseractPath("D:\\Tesseract-OCR");
		config.setTessdataPath("D:\\Tesseract-OCR\\tessdata");
		config.setLanguage("chi_sim");
	}

	/**
	 * 根据图片流识别图片上的内容
	 *
	 * @param imgStream 图片流
	 * @return 识别后的内容
	 */
	public static String parseImg(InputStream imgStream) throws Exception
	{
		ParseContext parseContext = new ParseContext();
		parseContext.set(TesseractOCRConfig.class, config);

		TesseractOCRParser ocrParser = new TesseractOCRParser();
		parseContext.set(TesseractOCRParser.class, ocrParser);

		ContentHandler contentHandler = new ToTextContentHandler();

		Metadata metadata = new Metadata();

		ocrParser.parse(imgStream, contentHandler, metadata, parseContext);
		return contentHandler.toString();
	}

	/**
	 * 识别pdf内容
	 *
	 * @param pdfStream pdf文件流
	 * @return 识别后的内容
	 */
	public static String parsePdf(InputStream pdfStream) throws Exception
	{
		PDFParser parser = new PDFParser();
		PDFParserConfig parserConfig = new PDFParserConfig();

		parserConfig.setExtractInlineImages(true);
		parserConfig.setExtractUniqueInlineImagesOnly(false);

		ContentHandler contentHandler = new ToTextContentHandler();

		ParseContext parseContext = new ParseContext();
		parseContext.set(PDFParser.class, parser);
		parseContext.set(PDFParserConfig.class, parserConfig);
		parseContext.set(TesseractOCRConfig.class, config);

		parser.parse(pdfStream, contentHandler, new Metadata(), parseContext);
		return contentHandler.toString();
	}

	/**
	 * 转化为带OCR的reader
	 *
	 * @param pdfStream pdf数据流
	 */
	public static Reader transformPdfReader(InputStream pdfStream, Metadata tikaMetadata) throws Exception
	{
		PDFParser parser = new PDFParser();
		PDFParserConfig parserConfig = new PDFParserConfig();

		parserConfig.setExtractInlineImages(true);
		parserConfig.setExtractUniqueInlineImagesOnly(false);

		ParseContext parseContext = new ParseContext();
		parseContext.set(PDFParser.class, parser);
		parseContext.set(PDFParserConfig.class, parserConfig);
		parseContext.set(TesseractOCRConfig.class, config);
		return new ParsingReader(parser, pdfStream, tikaMetadata, parseContext);
	}

	/**
	 * 转化为带OCR的reader
	 * @param imgStream img数据流
	 */
	public static Reader transformImgReader(InputStream imgStream, Metadata tikaMetadata) throws Exception
	{
		ParseContext parseContext = new ParseContext();
		parseContext.set(TesseractOCRConfig.class, config);

		TesseractOCRParser ocrParser = new TesseractOCRParser();
		parseContext.set(TesseractOCRParser.class, ocrParser);

		ContentHandler contentHandler = new ToTextContentHandler();

		ocrParser.parse(imgStream, contentHandler, tikaMetadata, parseContext);
		return new StringReader(contentHandler.toString());
	}
}