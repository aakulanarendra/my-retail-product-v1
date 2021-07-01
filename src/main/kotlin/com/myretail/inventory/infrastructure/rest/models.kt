package com.myretail.inventory.infrastructure.rest

data class ProductInfo(
  val product: Product
)

class Handling(
)

data class Packaging(
  val is_retail_ticketed: Boolean
)

data class ProductDescription(
  val bullet_description: List<String>,
  val downstream_description: String,
  val title: String
)

data class Fulfillment(
  val box_percent_filled_by_volume: Double,
  val box_percent_filled_by_weight: Double,
  val box_percent_filled_display: Double,
  val is_po_box_prohibited: Boolean,
  val po_box_prohibited_message: String
)

data class TaxCategory(
  val tax_class: String,
  val tax_code: String,
  val tax_code_id: Int
)

data class Item(
  val attributes: Attributes,
  val bundle_components: BundleComponents,
  val buy_url: String,
  val country_of_origin: String,
  val display_option: DisplayOption,
  val dpci: String,
  val eligibility_rules: EligibilityRules,
  val enrichment: Enrichment,
  val environmental_segmentation: EnvironmentalSegmentation,
  val estore_item_status_code: String,
  val fulfillment: Fulfillment,
  val handling: Handling,
  val is_cart_add_on: Boolean,
  val is_proposition_65: Boolean,
  val item_state: String,
  val manufacturer: Manufacturer,
  val mpaa_rating: MpaaRating,
  val package_dimensions: PackageDimensions,
  val packaging: Packaging,
  val product_brand: ProductBrand,
  val product_classification: ProductClassification,
  val product_description: ProductDescription,
  val product_vendors: List<ProductVendor>,
  val recall_compliance: RecallCompliance,
  val relationship_type_code: String,
  val return_method: String,
  val return_policies: ReturnPolicies,
  val ribbons: List<Any>,
  val ship_to_restriction: String,
  val specifications: List<Any>,
  val subscription_eligible: Boolean,
  val tags: List<Any>,
  val tax_category: TaxCategory,
  val tcin: String,
  val upc: String
)

class Manufacturer(
)

data class EnvironmentalSegmentation(
  val has_lead_disclosure: Boolean,
  val is_hazardous_material: Boolean
)

data class EligibilityRules(
  val add_on: AddOn
)

data class Enrichment(
  val images: List<Image>
)

data class ProductBrand(
  val brand: String,
  val facet_id: String,
  val manufacturer_brand: String
)

data class RecallCompliance(
  val is_product_recalled: Boolean
)

data class ModelsX(
  val product: Product
)

data class PackageDimensions(
  val depth: String,
  val dimension_unit_of_measure: String,
  val height: String,
  val weight: String,
  val weight_unit_of_measure: String,
  val width: String
)

data class ContentLabel(
  val image_url: String
)

data class ItemType(
  val name: String,
  val type: Int
)

class BundleComponents(
)

data class AvailableToPromiseNetwork(
  val availability: String,
  val availability_status: String,
  val available_to_promise_quantity: Double,
  val id_type: String,
  val is_infinite_inventory: Boolean,
  val is_loyalty_purchase_enabled: Boolean,
  val is_out_of_stock_in_all_online_locations: Boolean,
  val is_out_of_stock_in_all_store_locations: Boolean,
  val loyalty_availability_status: String,
  val loyalty_purchase_start_date_time: String,
  val multichannel_options: List<Any>,
  val online_available_to_promise_quantity: Double,
  val product_id: String,
  val stores_available_to_promise_quantity: Double,
  val street_date: String
)

data class ProductVendor(
  val id: String,
  val manufacturer_style: String,
  val vendor_name: String
)

data class DisplayOption(
  val is_size_chart: Boolean
)

data class ProductClassification(
  val item_type: ItemType,
  val item_type_name: String,
  val product_type: String,
  val product_type_name: String
)

data class Image(
  val base_url: String,
  val content_labels: List<ContentLabel>,
  val primary: String
)

data class MpaaRating(
  val image: String,
  val rating: String,
  val url: String
)

data class ReturnPolicies(
  val guestMessage: String,
  val policyDays: String,
  val user: String
)

data class Product(
  val available_to_promise_network: AvailableToPromiseNetwork,
  val item: Item
)

data class AddOn(
  val is_active: Boolean
)

data class Attributes(
  val gift_wrapable: String,
  val has_prop65: String,
  val is_hazmat: String,
  val manufacturing_brand: String,
  val max_order_qty: Int,
  val media_format: String,
  val merch_class: String,
  val merch_classid: Int,
  val merch_subclass: Int,
  val return_method: String,
  val ship_to_restriction: String,
  val street_date: String
)
