/*******************************************************************************
 * Copyright 2021 Stefan Holm
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ch.wsl.fps.hepromo.util;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ExportMethodResult {
	
	public final boolean success;
	public final boolean errorMsgShown;
	
	/**
	 * @param success indicates if the export method successfully created the desired file
	 * @param errorMsgShown indicates if there was already an error msg shown to the user if the creation of the file was not successfull
	 */
	public ExportMethodResult(boolean success, boolean errorMsgShown) {
		this.success = success;
		this.errorMsgShown = errorMsgShown;
	}

}
