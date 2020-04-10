package com.alex.loaders.interfaces;

public final class IComponentSettings {

    @SuppressWarnings("unused")
	private int settingsHash;
    @SuppressWarnings("unused")
    private int defaultHash;
    
    public IComponentSettings(int settingsHash, int defaultHash) { //not using atm but can be used for easy find which options unlock, easy as fk
    	    this.settingsHash = settingsHash;
    	    this.defaultHash = defaultHash;
    }
}
