/* Copyright 2004, Carnegie Mellon, All Rights Reserved */

package edu.cmu.minorthird.text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;

/**
 * Default version of AnnotatorLoader.
 *
 * @author William Cohen
 */

public class DefaultAnnotatorLoader extends AnnotatorLoader{
	
	private static Logger log=Logger.getLogger(DefaultAnnotatorLoader.class);

	/** Find the named resource file - usually a dictionary or trie for mixup. */
	public InputStream findFileResource(String fileName){
		log.debug("looking for file resource "+fileName);
		InputStream is=null;
		is=DefaultAnnotatorLoader.class.getClassLoader().getResourceAsStream(fileName);
		if(is==null){
			log.debug("here");
			is = ClassLoader.getSystemResourceAsStream(fileName);
		}
		if(is == null) {
			//DK: try the working directory
			try {
				is = new FileInputStream(fileName);
			} catch (FileNotFoundException e) {
				log.error(e.getLocalizedMessage(), e);
			}
		}
		return is;
	}

	/** Find the named resource class - usually an annotator. */
	public Class<?> findClassResource(String className){
		try{
			return DefaultAnnotatorLoader.class.getClassLoader().loadClass(className);
		}
		catch(ClassNotFoundException e){
			return null;
		}
	}
}
