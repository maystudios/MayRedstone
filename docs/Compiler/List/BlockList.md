# MayRedstone Compiler Block List

The goal of this project is to optimize redstone and process it on CUDA Kernel to speeds which are largely considered impossible by using compiler techniques inspired by UnrealEngine.

# Block List

# CUDA Representation
| State                                                                   | Cuda Redstone Base | Cuda Redstone Additional | Cuda Redstone Advanced |
|-------------------------------------------------------------------------|--------------------|--------------------------|------------------------|
| ![](https://img.shields.io/badge/Unpowered-8395a7?style=for-the-badge)  | 0                  | -                        | -                      |
| ![](https://img.shields.io/badge/Powered-ee5253?style=for-the-badge)    | 1                  | -                        | -                      |
| ![](https://img.shields.io/badge/Additional-ff9f43?style=for-the-badge) | -                  | 0 - 15                   | -                      |
| ![](https://img.shields.io/badge/Avanced-5f27cd?style=for-the-badge)    | -                  | -                        | 0 - 1                  |



## Blocks
| Image                                    | Block       | Cuda | States                                                                                                                                      |
|------------------------------------------|-------------|------|---------------------------------------------------------------------------------------------------------------------------------------------|
| ![](../../assests/Blocks/Base/Void.png)  | Void        | 0    | ![](https://img.shields.io/badge/Unpowered-8395a7?style=for-the-badge)                                                                      | 
| ![](../../assests/Blocks/Base/Solid.png) | Solid       | 1    | ![](https://img.shields.io/badge/Powered-ee5253?style=for-the-badge) ![](https://img.shields.io/badge/Unpowered-8395a7?style=for-the-badge) |
| ![](../../assests/Blocks/Base/Glass.png) | Transparent | 2    | ![](https://img.shields.io/badge/Unpowered-8395a7?style=for-the-badge)                                                                      |

### Redstone

#### Power Sources [Step 1]



| Image                                                              | Block          | Cuda | States                                                                                                                                      |
|--------------------------------------------------------------------|----------------|------|---------------------------------------------------------------------------------------------------------------------------------------------|
| ![](../../assests/Blocks/Redstone/PowerSources/redstone_block.png) | Redstone Block | 100  | ![](https://img.shields.io/badge/Powered-ee5253?style=for-the-badge)                                                                        |
| ![](../../assests/Blocks/Redstone/PowerSources/redstone_torch.png) | Redstone Torch | 101  | ![](https://img.shields.io/badge/Powered-ee5253?style=for-the-badge) ![](https://img.shields.io/badge/Unpowered-8395a7?style=for-the-badge) |
| ![](../../assests/Blocks/Redstone/PowerSources/lever.png)          | Lever          | 102  | ![](https://img.shields.io/badge/Powered-ee5253?style=for-the-badge) ![](https://img.shields.io/badge/Unpowered-8395a7?style=for-the-badge) |
| ![](../../assests/Blocks/Redstone/PowerSources/button.png)         | Button         | 103  | ![](https://img.shields.io/badge/Powered-ee5253?style=for-the-badge) ![](https://img.shields.io/badge/Unpowered-8395a7?style=for-the-badge) |
| ![](../../assests/Blocks/Redstone/PowerSources/pressure_plate.png) | Pressure Plate | 104  | ![](https://img.shields.io/badge/Powered-ee5253?style=for-the-badge) ![](https://img.shields.io/badge/Unpowered-8395a7?style=for-the-badge) |

#### Wiring [Step 2]

| Image                                                             | Block               | Cuda | States                                                                                                                                                                                                                                                                                      |
|-------------------------------------------------------------------|---------------------|------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ![](../../assests/Blocks/Redstone/Wiring/redstone_dust.png)       | Redstone Dust       | 200  | ![](https://img.shields.io/badge/Powered-ee5253?style=for-the-badge) ![](https://img.shields.io/badge/Unpowered-8395a7?style=for-the-badge)  ![](https://img.shields.io/badge/Strength-0>>15-ff9f43?style=for-the-badge)                                                                    |
| ![](../../assests/Blocks/Redstone/Wiring/redstone_repeater.png)   | Redstone Reapeater  | 210  | ![](https://img.shields.io/badge/Powered-ee5253?style=for-the-badge) ![](https://img.shields.io/badge/Unpowered-8395a7?style=for-the-badge)  ![](https://img.shields.io/badge/Delay-1>>4-ff9f43?style=for-the-badge) ![](https://img.shields.io/badge/Locked-5f27cd?style=for-the-badge)    |
| ![](../../assests/Blocks/Redstone/Wiring/redstone_comperator.png) | Redstone Comperator | 220  | ![](https://img.shields.io/badge/Powered-ee5253?style=for-the-badge) ![](https://img.shields.io/badge/Unpowered-8395a7?style=for-the-badge)  ![](https://img.shields.io/badge/Compare-5f27cd?style=for-the-badge) ![](https://img.shields.io/badge/Substract-5f27cd?style=for-the-badge)    |

#### Run [Step 3]

| Image                                                             | Block         | Cuda | States                   |
|-------------------------------------------------------------------|---------------|------|--------------------------|
| ![](../../assests/Blocks/Redstone/Device/redstone_lamp.png)       | Redstone Lamp | 300  | ![](https://img.shields.io/badge/Powered-ee5253?style=for-the-badge) ![](https://img.shields.io/badge/Unpowered-8395a7?style=for-the-badge) |

# Stages

The compilation progresss will be split into stages as follows:

1. Set the powered states in the `GPU Allocation Memory`
2. Process base `Inputs` like Buttons or Plates ... and set Block powered state through `CUDA Kernel`
3. Run the `Wiring Kernel` 15 times to process dust strength
4. Run the `Base Compiler Kernel` to process redstone devices


