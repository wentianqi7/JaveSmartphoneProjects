package adapter;

import scale.SynEditAuto;
import server.AutoServer;

/**
 * class implements create, update, fix of automobile for external usage
 * 
 * @author Tianqi Wen (tianqiw)
 * 
 */
public class BuildAuto extends ProxyAutomobile implements CreateAuto,
		UpdateAuto, FixAuto, ChooseAuto, SynEditAuto, AutoServer {

}
