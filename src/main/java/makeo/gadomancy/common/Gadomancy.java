package makeo.gadomancy.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import makeo.gadomancy.api.GadomancyApi;
import makeo.gadomancy.common.api.DefaultApiHandler;
import makeo.gadomancy.common.data.ModConfig;
import makeo.gadomancy.common.data.ModData;

/**
 * This class is part of the Gadomancy Mod
 * Gadomancy is Open Source and distributed under the
 * GNU LESSER GENERAL PUBLIC LICENSE
 * for more read the LICENSE file
 *
 * Created by makeo @ 29.11.2014 14:07
 */
@Mod(modid = Gadomancy.MODID, name = Gadomancy.NAME, version = Gadomancy.VERSION, dependencies="required-after:Thaumcraft@[4.1.1.11,);after:Waila")
public class Gadomancy
{
    public static final String MODID = "gadomancy";
    public static final String NAME = "Gadomancy";

    public static final String VERSION = "1.0.0";

    private static final String PROXY_CLIENT = "makeo.gadomancy.client.ClientProxy";
    private static final String PROXY_SERVER = "makeo.gadomancy.common.CommonProxy";

    private static ModData modData = null;
    public static ModData getModData() {
        return modData;
    }

    public static void loadModData() {
        modData = new ModData("data");
        modData.load();
    }

    public static void unloadModData() {
        if(modData != null) {
            modData.save();
            modData = null;
        }
    }

    @Mod.Instance(value = Gadomancy.MODID)
    public static Gadomancy instance;

    @SidedProxy(clientSide = PROXY_CLIENT, serverSide = PROXY_SERVER)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void onConstruct(FMLConstructionEvent event) {
        GadomancyApi.setInstance(new DefaultApiHandler());
        proxy.onConstruct();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInitalize();
        ModConfig.init(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.initalize();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInitalize();
    }
}