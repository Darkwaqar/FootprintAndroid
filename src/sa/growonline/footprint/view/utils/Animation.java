/*
 * Copyright (c) 2016 Arsal Raza Imam
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
 */
package sa.growonline.footprint.view.utils;

import sa.growonline.footprint.view.GestureImageView;

/**
 * @author Jason Polites
 *
 */
public interface Animation {
	
	/**
	 * Transforms the view.
	 * @param view
	 * @param diffTime
	 * @return true if this animation should remain active.  False otherwise.
	 */
	public boolean update(GestureImageView view, long time);
	
}
