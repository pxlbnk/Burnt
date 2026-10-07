# Adding your blocks to Burnt (for mod & datapack authors)

Burnt makes wood and plants actually burn down — blocks catch fire, smolder, and end up
charred. If you make a mod (or datapack) with your own wooden blocks, you can plug them
into this system **without writing any code and without asking us**. You just ship a
couple of small JSON files inside your own mod or datapack.

There are two ways in. Pick whichever fits:

| You have… | Use |
|---|---|
| A custom block that should turn into one of **Burnt's** smoldering/burnt blocks (e.g. your log → Burnt's smoldering log) | **Way 1: tags** (easiest, best for many blocks) or **Way 2: exact mappings** |
| A custom block **plus your own** smoldering/burnt versions of it | **Way 3: custom chain** |

Nothing here requires Burnt as a dependency to compile against. It's all data files.
If Burnt isn't installed, your files are simply ignored.

---

## Way 1: Tags — "my block burns like a …"

Burnt decides how a block burns by checking which *tag* it's in. Tags are Minecraft's
standard grouping system, and **any mod or datapack can add entries to another mod's
tags** — the game merges them all together. So you can add your blocks to Burnt's tags
from your own jar, and it Just Works.

**Where the file goes** (inside your mod's `resources` or your datapack):

- Minecraft **1.21.x**: `data/burnt/tags/block/<tag_name>.json`
- Minecraft **1.20.1**: `data/burnt/tags/blocks/<tag_name>.json`  *(note: "blocks", plural)*

**What the file looks like** — say your mod is `mymod` and you want your logs to burn
like standard logs. Create `data/burnt/tags/block/burns_to_smoldering_log.json`:

```json
{
  "replace": false,
  "values": [
    "mymod:cedar_log",
    "mymod:cedar_wood"
  ]
}
```

That's it. `"replace": false` means "add to the tag, don't wipe it" — always use that.
Once a block is in one of these tags, fire will spread to it, it converts to the right
smoldering block (keeping properties like axis/facing/waterlogged where possible),
smolders for a while, and chars down — all at speeds controlled by the player's Burnt
config.

### The tag menu

Pick the tag that best matches what your block *is*:

**Trees & wood**

| Tag | Use for | Behaviour |
|---|---|---|
| `burns_to_smoldering_log` | Standard logs | Normal log burn speed |
| `burns_to_smoldering_log_slow` | Dense/hard logs | Burns noticeably slower |
| `burns_to_hollow_log` | Hollow logs | Burns through smoldering → ember → (rare) blazing → burnt, like a log, but ends as a hollow burnt log — no sapling replant. Ships pre-populated with Quark's hollow logs (`required:false`); add your mod's own. Also smokeable: any item in the `#burnt:hollow_logs` **item** tag (same Quark list, `required:false`, extensible) smokes in a smoker to `burnt_hollow_log`. |
| `burns_to_small_log` | Thin logs, posts, branches that aren't a full block | Small smoldering log |
| `burns_to_smoldering_wood` | Bark-on-all-sides "wood" blocks | Like a log |
| `burns_to_smoldering_stripped_log` | Stripped logs | |
| `burns_to_smoldering_stripped_wood` | Stripped wood | |
| `burns_to_smoldering_branch` | Branch blocks | |
| `burns_to_smoldering_root` | Root blocks (like mangrove roots) | |

**Leaves** (pick by how delicate the foliage looks)

| Tag | Use for |
|---|---|
| `burns_to_large_leaves` | Big, dense leaf blocks |
| `burns_to_medium_leaves` | Ordinary leaves |
| `burns_to_fine_leaves` | Wispy/delicate foliage — burns away fastest |

**Built wood**

| Tag | Use for |
|---|---|
| `burns_to_smoldering_planks` | Planks |
| `burns_to_smoldering_stairs` | Wooden stairs |
| `burns_to_smoldering_slabs` | Wooden slabs |
| `burns_to_smoldering_fences` | Wooden fences |
| `burns_to_smoldering_fence_gates` | Fence gates |
| `burns_to_smoldering_door` | Wooden doors |
| `burns_to_smoldering_trapdoor` | Wooden trapdoors |
| `burns_to_smoldering_button` | Wooden buttons |
| `burns_to_smoldering_pressure_plate` | Wooden pressure plates |

**Ground & plants**

| Tag | Use for |
|---|---|
| `burns_to_smoldering_grass` | Grass-type ground blocks → end up as burnt grass |
| `burns_to_burnt_dirt` | Dirt-like ground → burnt dirt |
| `burns_to_burnt_farmland` | Farmland variants |
| `burns_to_smoldering_crops` | Crops |
| `burns_to_smoldering_fern` | Ferns |
| `burns_to_smoldering_tall_grass` | Tall grass / two-block plants |
| `burns_to_smoldering_moss` | Moss blocks |
| `burns_to_smoldering_moss_carpet` | Moss carpets |

**Soft goods & misc**

| Tag | Use for |
|---|---|
| `burns_to_smoldering_wool` | Wool-like blocks |
| `burns_to_smoldering_carpet` | Carpets |
| `burns_to_smoldering_envelope` | Airship envelopes (Create Aeronautics-style) |
| `burns_to_smoldering_sail` / `_sail_frame` / `_symmetric_sail` | Sails |
| `destroy_in_burn` | "Just delete it" — the block breaks when fire reaches it, no burnt version, no drops. Good for fragile decorations. |

**Being in any of these tags automatically makes your block a target for fire spread** —
you don't need to do anything else.

### Opting blocks OUT of burning

The reverse also works. If Burnt is burning something of yours that shouldn't burn
(magic wood, fireproof structure blocks, boss arenas):

| Tag | Effect |
|---|---|
| `will_not_burn` | Fire never spreads to it and never converts it |
| `fire_resistant` | Same idea — treated as fireproof |

Example — `data/burnt/tags/block/will_not_burn.json`:

```json
{
  "replace": false,
  "values": [
    "mymod:enchanted_planks"
  ]
}
```

### Keeping vanilla fire on a block (for mods that read fire events)

Some mods watch for **vanilla `minecraft:fire`** appearing or burning out on top of a
specific block — for example **Ender IO** drops Grains of Infinity (and a Suspicious Seed)
when fire dies out on bedrock/deepslate. By default Burnt replaces vanilla fire with its
own fire block the moment it's placed, so those mods never see the vanilla fire and their
handler never runs.

Put a block in **`vanilla_fire_burns_on`** and Burnt leaves ordinary vanilla `minecraft:fire` on
top of it — it never swaps in Burnt's fire block there. The block still doesn't burn; you
just get plain vanilla fire that flickers out naturally, and anything listening for that
keeps working.

| Tag | Effect |
|---|---|
| `vanilla_fire_burns_on` | Fire placed on top of this block stays vanilla `minecraft:fire` instead of becoming Burnt's fire |

Burnt already ships bedrock, deepslate and the whole stone family (stone/granite/diorite/
andesite/tuff/deepslate + their bricks, cobblestone, blackstone, basalt, calcite, …) in
this tag. Add your own non-burning blocks the same way —
`data/burnt/tags/block/vanilla_fire_burns_on.json` (1.21.x) or `.../tags/blocks/...` (1.20.1):

```json
{
  "replace": false,
  "values": [
    "mymod:machine_casing",
    "mymod:reinforced_stone"
  ]
}
```

Use it for solid, non-flammable bases (machine blocks, stone-like decoration, structure
blocks) where you want plain vanilla fire behaviour — and vanilla fire events — preserved.
The check looks at the block **directly under** the fire.

---

## Way 2: Exact mappings — "this block becomes exactly that block"

If tags are too coarse — you want *this specific block* to become *that specific block*
— use a **burn mapping** file instead. Burnt reads these from **every** mod and datapack.

**Where:** `data/<your_namespace>/burn_mappings/<any_name>.json` (your own namespace,
any filename — works the same on 1.20.1 and 1.21.x).

**Format:**

```json
{
  "mappings": [
    { "from": "mymod:cedar_log",  "to": "burnt:smoldering_log" },
    { "from": "mymod:cedar_wood", "to": "burnt:smoldering_wood" }
  ]
}
```

- When fire reaches the `from` block, it becomes the `to` block.
- Block properties (axis, facing, waterlogged…) are copied over automatically where the
  destination has them.
- A mapping **overrides everything**, including the tags above — so it's also the way to
  make one specific block behave differently from the rest of its tag.
- Having a mapping automatically makes the block a fire-spread target too.
- Typos and missing blocks are skipped with a log warning — they never crash the game.

There's a commented example you can copy in Burnt's own files:
`data/burnt/burn_mappings/example.json`.

---

## Way 3: Your own smoldering & burnt blocks (custom chain)

If you've made your *own* smoldering and burnt versions of your block and want Burnt's
fire to drive them:

**1. Ignition — works today, data only.** Point a burn mapping at *your* block instead
of ours:

```json
{
  "mappings": [
    { "from": "mymod:cedar_log", "to": "mymod:smoldering_cedar_log" }
  ]
}
```

When Burnt's fire reaches your log, it becomes *your* smoldering log. Fire spread
targets it automatically because the mapping exists.

**2. The smoldering lifecycle — your block, your code.** Burnt cannot tick another
mod's block (Minecraft dispatches block ticks to the block's own class). So *your*
smoldering block is responsible for its own progression — advancing visual stages,
eventually turning into your burnt block, going out in rain, and so on. In practice
that's a scheduled tick every ~20 ticks plus a random roll, same as vanilla-style
burning blocks. If you'd like your burn speed to follow the player's Burnt config
sliders, get in touch — we're open to exposing a small helper for that if there's
demand.

**3. Spreading fire FROM your block — one method call.** To make your smoldering
block ignite its surroundings the way Burnt's blocks do, call:

```java
net.pixelbank.burnt.procedures.WillitburnProcedure.execute(level, x, y, z);
```

(`x/y/z` are your block's position — the signature takes doubles.)

One call does everything Burnt's own burning blocks do: it respects the
`doFireTick` gamerule and Burnt's master on/off config, backs off in rain (when the
player has rain-extinguish enabled) and near water, then attempts to ignite the
surrounding blocks — sides reliably, upward more eagerly, downward and diagonals a
little less, with wind direction factored in if the player has wind enabled. Every
neighbour it ignites starts at the *beginning* of its own burn, so the fire front
still advances at a natural pace.

Two things to know:

- **Call it once, not constantly.** One call is a full burst of spread attempts —
  adjacent flammable blocks *will* catch. Burnt's own blocks spend a config-driven
  while smoldering quietly and only make this call when they reach their fully-alight
  stage. Do the same: the *pacing* of your fire is how long your block waits before
  calling this, not how often you call it.
- **Make Burnt optional the safe way.** Put the call in its own small class and only
  invoke that class after checking Burnt is installed —
  `ModList.get().isLoaded("burnt")` on Forge/NeoForge,
  `FabricLoader.getInstance().isModLoaded("burnt")` on Fabric. Java only loads a class
  the first time it's used, so keeping the Burnt call in a separate class means your
  mod still runs fine when Burnt isn't there. To compile against Burnt, pull the
  released jar via Curse Maven or Modrinth Maven as a `compileOnly` dependency (or
  drop it in a local `libs` folder).

If you need finer control (ignite one specific position rather than a spread burst),
`net.pixelbank.burnt.procedures.BurnBabyProcedure.execute(level, x, y, z)` converts
the single block at that position through Burnt's normal rules — it still respects
the fireproof opt-out tags. Note it does *not* check the gamerule/config/rain gates
on its own; `WillitburnProcedure` is the "do the right thing" entry point.

**4. Recognition — make the rest of Burnt see your fire.** Burnt has systems that react
to "something is on fire here": the smoke detector block, heat-haze and smoke-fog
effects, and firefighting iron golems. To join in, add your smoldering block to these
tags (same merge trick as Way 1):

| Tag | What it makes happen |
|---|---|
| `on_fire` | Smoke detectors trip near your block; iron golems come to fight it; the fire extinguisher recognises it |
| `fire` | Your block counts toward heat-haze and smoke-fog intensity around players |

**One honest caveat:** Burnt's fire extinguisher and iron golems know how to *put out*
Burnt's own blocks (each conversion is specific). They'll walk to / spray at your
smoldering block, but they can't yet convert it to your burnt version — your block
should also handle being extinguished itself (water, splash bottles, snowballs — and
ideally the golem's stomp area). If enough mods want it, we'll add a data-driven
"extinguish mapping" to close this gap — tell us.

---

## Testing your integration

1. Install Burnt alongside your mod.
2. `/tag` won't show block tags directly, but you can check membership in-game with
   F3 + targeting the block (tags show in the right panel on 1.20.1+), or just:
3. Flint & steel your block. Within a few seconds it should convert (tags/mappings are
   read on world load and on `/reload`).
4. Check the log for lines like `[Burnt] Loaded N burn mapping(s) from datapacks.` and
   warnings about unknown block ids — that's where typos show up.

## Notes & promises

- These tag names, the `burn_mappings` format, and the two Java entry points named
  above (`WillitburnProcedure.execute` and `BurnBabyProcedure.execute`, both
  `(LevelAccessor, double, double, double)`) are **stable API** — we won't rename or
  change them without a major version and a changelog notice.
- Everything merges: multiple mods can extend the same tags and ship their own mapping
  files without conflicting.
- Questions, requests (extinguish mappings, config-speed helpers, a tier that doesn't
  exist yet)? Open an issue or ask on the Discord.
