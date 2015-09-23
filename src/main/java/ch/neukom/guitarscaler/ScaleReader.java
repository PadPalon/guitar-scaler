package ch.neukom.guitarscaler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import com.google.common.collect.Maps;

public class ScaleReader {
	public Map<String, ScaleDefinition> readScales() {
		Map<String, ScaleDefinition> scales = Maps.newHashMap();
		
		File scalesFolder = new File(getClass().getResource("/scales").getPath());
		for(File scaleFile: scalesFolder.listFiles()) {
			ScaleDefinition scale = new ScaleDefinition();
			
			FileInputStream input = null;
			BufferedReader reader = null;
			try {
				input = new FileInputStream(scaleFile);
				reader = new BufferedReader(new InputStreamReader(input));
				
				String line;
				while((line = reader.readLine()) != null) {
					scale.addStep(Integer.valueOf(line));
				}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(input != null) {
						input.close();
					}
					if(reader != null) {
						reader.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			scales.put(scaleFile.getName(), scale.finishScale());
		}
		
		return scales;
	}
}
