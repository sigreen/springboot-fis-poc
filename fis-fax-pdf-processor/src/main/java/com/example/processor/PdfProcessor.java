package com.example.processor;

import java.io.File;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PdfProcessor implements Processor {
  public void process(Exchange exchange) throws Exception {
	  PDDocument document = PDDocument.load((File) exchange.getIn().getBody());
	  Splitter splitter = new Splitter();
	  List<PDDocument> splittedDocuments = splitter.split(document);
	  exchange.getIn().setBody(splittedDocuments);
  }
}