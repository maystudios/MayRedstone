# Minecraft High-Performance Redstone Server

[![Build Status](https://travis-ci.org/MCHPR/MCHPRS.svg?branch=master)](https://travis-ci.org/MCHPR/MCHPRS) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![Discord Banner 2](https://discordapp.com/api/guilds/724072903083163679/widget.png)](https://discord.com/invite/svK9JU7)

A Minecraft 1.16.5 creative client Mid built for redstone. You can create Plots in Chunk Dimensions up to 32 * 32 (smallest 1 * 1) and every chunk outside this is deactivated, allowing for less lag, more concurrency!

MayRedstone is very different from the traditional redstone Compiler. Because this Client mod allows to disable specific Block Updates or Block Updates in specific areas. (However the Blocks gets updated in background without visibility).

MCHPRS has made it possible to run programs such as [Graph Rendering, Conway's Game of Life, and Mandelbrot Rendering](https://www.youtube.com/watch?v=FDiapbD0Xfg) on CPUs in Minecraft. To accomplish these speeds, we created [MayCompiler](docs/MayCompiler.md), the "Redstone Compiler".

## Table of Contents

- [Table of Contents](#table-of-contents)
- [Building](#building)
- [Configuration](#configuration)
- [Usage](#usage)
   - [General Commands](#general-commands)
   - [Plot Creation](#plot-creation)
   - [Menu](#menu)
   - [Start](#start)
   - [Areas](#areas)
- [Acknowledgments](#acknowledgments)
- [Contributing](#contributing)
- [License](#license)

## Building

If the NVCC compiler is not already installed, you can find out how [on their official website](https://visualstudio.microsoft.com/de/downloads/).

```shell
git clone https://github.com/CrazyKnight144hz/MayRedstone.git
cd MayRedstone
cargo build --release
```

Once complete, the optimized executable will be located at `./target/release/mayredstone` or `./target/release/mayredstone.exe` depending on your operating system.

## Configuration

MayRedstone will generate a `Config.toml` file in the current working directory or you can edit it through the game settings.

## Usage

### General Commands
| Command | Alias | Description |
| --- | --- |--- |
| `/rtps [rtps]` | None | Set the **redstone** ticks per second in the plot to `[rtps]`. (There are two game ticks in a redstone tick) |
| `/radvance [ticks]` | `/radv` | Advances the plot by `[ticks]` redstone ticks. |
| `/teleport [player]` | `/tp` | Teleports you to `[player]`. |
| `/teleport [x] [y] [z]` | `/tp` | Teleports you to `[x] [y] [z]`. Supports relative coordinates. Floats can be expressed as described [here](https://doc.rust-lang.org/std/primitive.f64.html#grammar). |
| `/speed [speed]` | None | Sets your flyspeed. |
| `/gamemode [mode]` | `/gmc`, `/gmsp` | Sets your gamemode. |
| `/container [type] [power]` | None | Gives you a container (e.g. barrel) which outputs a specified amount of power when used with a comparator. |
| `/redpiler compile` | `/rp c` | Manually starts redpiler compilation. Available flags: --io-only --optimize --export (or in short: -I -O -E) |
| `/redpiler reset` | `/rp r` | Stops redpiler. |
| `/toggleautorp` | None | Toggles automatic redpiler compilation. |
| `/stop` | None | Stops the server. |

### Plot Creation
The plot creation system in MayRedstone is very incomplete.
These are the commands that are currently implemented:
| Command | Alias | Description |
| --- | --- |--- |
| `/plot info` | `/p i` | Gets the owner of the plot you are in. |
| `/plot claim` | `/p c` | Claims the plot you are in if it is not already claimed. |
| `/plot auto` | `/p a` | Automatically finds an unclaimed plot and claims. |
| `/plot middle` | None | Teleports you to the center of the plot you are in. |
| `/plot visit [player]` | `/p v` | Teleports you to a player's plot. |
| `/plot tp [x] [z]` | None | Teleports you to the plot at `[x] [y]`. Supports relative coordinates. |
| `/plot lock` | None | Locks the player into the plot so moving outside of the plot bounds does not transfer you to other plots. |
| `/plot unlock` | None | Reverses the locking done by `/plot lock`. |

### Menu


## Acknowledgments
- [@AL1L](https://github.com/AL1L) for his contributions to worldedit and other various features.
- [@DavidGarland](https://github.com/DavidGarland) for a faster and overall better implementation of `get_entry` in the in-memory storage. This simple function runs 30% of the runtime for redstone.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)
