# Promo screenshots

The images used for the CurseForge and Modrinth listings.

These used to sit in every tree's `src/main/resources/assets/burnt/Screenshots/`, which meant
all 14 of them (12.1 MB) were packaged into every jar - about 60% of the download for files the
game never reads. They live here instead: one shared copy, outside every resource root, so they
stay in the repo for the listings without shipping to players.

Don't move them back under `src/main/resources`. Anything in there goes into the jar.
