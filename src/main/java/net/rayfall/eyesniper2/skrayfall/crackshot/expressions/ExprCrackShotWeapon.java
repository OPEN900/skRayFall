package net.rayfall.eyesniper2.skrayfall.crackshot.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import com.shampaggon.crackshot.CSUtility;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

@Name("CrackShot Weapon")
@Description("This expression represents weapon interactions with crackshot. The text is the weapons name.")
@RequiredPlugins("CrackShot")
public class ExprCrackShotWeapon extends SimpleExpression<ItemStack> {

    // (gun|crackshot weapon) %string%

    private Expression<String> name;

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        name = (Expression<String>) exp[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    @Nullable
    protected ItemStack[] get(Event evt) {
        // TODO Auto-generated method stub
        CSUtility cs = new CSUtility();
        ItemStack weapon = cs.generateWeapon(name.getSingle(evt).replace("\"", ""));
        if (weapon != null) {
            return new ItemStack[]{weapon};
        } else {
            Skript.error("The weapon " + name.getSingle(evt).replace("\"", "") + " does not exist!");
            return new ItemStack[]{};
        }
    }
}
