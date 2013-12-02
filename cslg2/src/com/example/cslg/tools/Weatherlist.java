package com.example.cslg.tools;

import java.io.Serializable;

public class Weatherlist  implements Serializable{
private String _uptime;
private String _wendu;
private String _level;
public String get_uptime() {
	return _uptime;
}
public void set_uptime(String _uptime) {
	this._uptime = _uptime;
}
public String get_wendu() {
	return _wendu;
}
public void set_wendu(String _wendu) {
	this._wendu = _wendu;
}
public String get_level() {
	return _level;
}
public void set_level(String _level) {
	this._level = _level;
}
}
