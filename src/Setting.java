import java.util.Collection;

public class Setting {
	private Cityscape cityscape;
	
	public Setting() {
		this.cityscape = new Cityscape();
		
		Collection<Faction> c = null;
		cityscape.addAllCurrentMembers(c);
		
	}

}
