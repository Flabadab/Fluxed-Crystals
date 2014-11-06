package fluxedCrops;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Jared on 11/2/2014.
 */
public class OreDictUtils {


    public static ItemStack getItem(String oredict) {

        if (OreDictionary.getOres(oredict).get(0) != null)
            return OreDictionary.getOres(oredict).get(0);

        return null;
    }

}
