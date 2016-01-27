package cc.cmu.edu.minisite.services;

import cc.cmu.edu.minisite.database.AccountHandler;
import cc.cmu.edu.minisite.database.ShakeHandler;
import cc.cmu.edu.minisite.database.SongHandler;

public class RequestHandler extends HandlerProxy implements SongHandler,
		ShakeHandler, AccountHandler {

}
