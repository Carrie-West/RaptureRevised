package items;

public enum ITEM{
		Magazine ("Magazine"),
		Manual 	 ("Safety Manual");
		
		ITEM(String name) {
			this.name = name;
		}

		private String name;
}
