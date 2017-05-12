package com.example.processor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class PDFToTIFF implements Processor {
	public void process(Exchange exchange) throws Exception {
		byte[] pdf = (byte[]) exchange.getIn().getBody();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			// load PDF document
			PDDocument document = PDDocument.load(pdf);

			// create PDF renderer
			PDFRenderer renderer = new PDFRenderer(document);

			// go through each page of PDF, and generate TIF for each PDF page.
			for (int i = 0; i < document.getNumberOfPages(); i++) {
				// Returns the given page as an RGB image with 300 DPI.
				BufferedImage image = renderer.renderImageWithDPI(i, 300, ImageType.BINARY);

				// Writes a buffered image to a file using the given image
				// format.
				ImageIOUtil.writeImage(image, "tif", baos);
				image.flush();
				exchange.getIn().setBody(baos.toByteArray());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
