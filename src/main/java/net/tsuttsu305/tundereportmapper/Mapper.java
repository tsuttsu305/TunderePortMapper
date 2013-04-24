/**
 * TunderePortMapper - Package: net.tsuttsu305.tundereportmapper
 * Created: 2013/04/23 18:12:42
 */
package net.tsuttsu305.tundereportmapper;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import org.teleal.cling.UpnpService;
import org.teleal.cling.UpnpServiceImpl;
import org.teleal.cling.support.igd.PortMappingListener;
import org.teleal.cling.support.model.PortMapping;

/**
 * Mapper (Mapper.java)
 * @author tsuttsu305
 */
public class Mapper {
    private TunderePortMapper plugin;
    
    private UpnpService upnpService = null;
    private final int port;

    public Mapper(int port, TunderePortMapper plugin) {
        this.plugin = plugin;
        this.port = port;
    }

    public void openTCP() throws UnknownHostException{
        PortMapping desiredMapping = new PortMapping( port, Inet4Address.getLocalHost().getHostAddress(), PortMapping.Protocol.TCP, "Minecraft Multi Play");

        upnpService = new UpnpServiceImpl(new PortMappingListener(desiredMapping));
        
        upnpService.getControlPoint().search();
        
        plugin.getLogger().info("Port Mapping SUCCESS. Port: " + port);
    }
    
    public void closeTCP(){
        upnpService.shutdown();
    }
}

