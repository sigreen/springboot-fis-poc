package com.example.processor;


import java.io.ByteArrayOutputStream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.pdfbox.pdmodel.PDDocument;

public class SaveSinglePdf implements Processor {
  public void process(Exchange exchange) throws Exception {
	  PDDocument document = (PDDocument) exchange.getIn().getBody();
	  ByteArrayOutputStream baos = new ByteArrayOutputStream();
	  document.save(baos);
	  document.close();
	  exchange.getIn().setBody(baos.toByteArray());
  }
}