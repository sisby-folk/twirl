<!--suppress HtmlDeprecatedTag, XmlDeprecatedElement -->


<center>
<img alt="preview" src="https://cdn.modrinth.com/data/l6f8KaeX/images/3ab13fc49c9efeeca249fa07337c7a5e1abe1acc.gif"><br/>
Adds a less-than-useless enchantment (curse) that replaces the use action with a twirl!<br/>
<i>I'll try spinning! That's a good trick!</i><br/>
</center>

---

Any twirling-enchanted item can be used to visually spin around!

This prevents utilizing the usual use for an item, like drawing a bow or tilling soil.

Books can be enchanted with twirling at a crafting table using feathers, wind charges, or breeze rods.

### Datapacking

Item-specific behaviour can be altered with these item tags:
- `#twirl:keep_use` - allows the original item's `use()` to run.
- `#twirl:keep_tick` - allows the original item's `usageTick()` to run.
- `#twirl:keep_finish` - allows the original item's `onStoppedUsing()` to run.
- `#twirl:keep_action` - preserves the original item's `getUseAction()` result.
- `#twirl:rotate_z` - spin in the other sensible axis instead.

## Afterword

All mods are built on the work of many others.

This mod was made for ModFest: Carnival!

We're open to suggestions for how to implement stuff better - if you see something wonky and have an idea - let us know.
