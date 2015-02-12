package com.fingled.android.tools.iconizer.handler;

import com.fingled.android.tools.iconizer.IconSize;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.*;

/**
 * {@link com.fingled.android.tools.iconizer.IconSize}
 * {@link com.fingled.android.tools.iconizer.IconSize.IconType}
 * <br />
 * {@link OptionHandler}
 * <p/>
 * Created by Robert Peralta on 2/12/2015.
 */
public class IconTypeHandler extends OneArgumentOptionHandler<IconSize.IconType>
{

    public IconTypeHandler(CmdLineParser parser, OptionDef option, Setter<? super IconSize.IconType> setter)
    {
        super(parser, option, setter);
    }

    @Override
    protected IconSize.IconType parse(String argument) throws CmdLineException
    {
        IconSize.IconType result = IconSize.IconType.CONTEXTUAL;
        if (argument != null && argument.length() > 0)
            for (IconSize.IconType type : IconSize.IconType.values())
            {
                if (type.name().equalsIgnoreCase(argument))
                    result = type;
            }
        return result;
    }

    @Override
    public String getDefaultMetaVariable()
    {
        return IconSize.IconType.class.getName().toUpperCase();
    }
}
