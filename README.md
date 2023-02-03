# MayRedstone GPU Compiler

[![Build Status](https://travis-ci.org/MCHPR/MCHPRS.svg?branch=master)](https://travis-ci.org/MCHPR/MCHPRS) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://www.apache.org/licenses/LICENSE-2.0) [![Discord Banner 2](https://discordapp.com/api/guilds/724072903083163679/widget.png)](https://discord.com/invite/svK9JU7)

A Minecraft 1.16.5 creative client Mid built for redstone. You can create Plots in Chunk Dimensions up to 32 * 32 (smallest 1 * 1) and every chunk outside this is deactivated, allowing for less lag, more concurrency!

MayRedstone is very different from the traditional redstone Compiler. Because this Client mod allows to disable specific Block Updates or Block Updates in specific areas. (However the Blocks gets updated in background without visibility).

MCHPRS has made it possible to run programs such as [Graph Rendering, Conway's Game of Life, and Mandelbrot Rendering](https://www.youtube.com/watch?v=FDiapbD0Xfg) on CPUs in Minecraft. To accomplish these speeds, we created [MayCompiler](docs/Compiler/Redstone/MayCompiler.md), the "Redstone Compiler".

## Table of Contents

- [Table of Contents](#table-of-contents)
- [Building](#building)
- [Configuration](#configuration)
- [Usage](#usage)
   - [General Commands](#general-commands)
   - [World Creation](#world-creation)
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

### World Creation
The plot creation system in MayRedstone is very incomplete.
These are the commands that are currently implemented:

### Menu

### Start
| Command              | Alias | Description                                                                  |
|----------------------| --- |------------------------------------------------------------------------------|
| `/may start [start]` | None | Start the **MayRedstone GPU Compiler**. (Run not visible (No Block Updates)) |
| `/may stop  [stop]`  | None | stop the **MayRedstone GPU Compiler**.                                       |


### Areas
| Command              | Alias | Description                                                                  |
|----------------------| --- |------------------------------------------------------------------------------|
| `/may scan  [scan]`  | None | Scan blocks in Area to allocate them on the GPU.                             |

## Acknowledgments
- [@ChatGPT](https://openai.com/blog/chatgpt/) for explaining me erros LAMAO


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)
