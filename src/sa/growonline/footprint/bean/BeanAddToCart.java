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

package sa.growonline.footprint.bean;

public class BeanAddToCart
{
	private String AvailableForPreOrder, CustomerEnteredPrice, CustomerEnteredPriceRange, CustomerEntersPrice,
					DisableBuyButton, DisableWishlistButton, EnteredQuantity, IsRental, PreOrderAvailabilityStartDateTimeUtc,
						ProductId, UpdatedShoppingCartItemId;

	public String getEnteredQuantity() {
		return EnteredQuantity;
	}

	public void setEnteredQuantity(String enteredQuantity) {
		EnteredQuantity = enteredQuantity;
	}

	public String getUpdatedShoppingCartItemId() {
		return UpdatedShoppingCartItemId;
	}

	public void setUpdatedShoppingCartItemId(String updatedShoppingCartItemId) {
		UpdatedShoppingCartItemId = updatedShoppingCartItemId;
	}

	public String getDisableBuyButton() {
		return DisableBuyButton;
	}

	public void setDisableBuyButton(String disableBuyButton) {
		DisableBuyButton = disableBuyButton;
	}
}