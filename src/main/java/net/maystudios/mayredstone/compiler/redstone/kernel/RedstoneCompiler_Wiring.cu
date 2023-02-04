extern "C"

struct Block {
    int BlockType;
    int BlockState;
    int BlockAdditional;
    int BlockAdvanced;
    int BlockDirection;
};

__global__ void RedstoneCompiler_Wiring(Block *blocks, Blocks* newBlocks) {
    int x = blockIdx.x * blockDim.x + threadIdx.x;
    int y = blockIdx.y * blockDim.y + threadIdx.y;
    int z = blockIdx.z * blockDim.z + threadIdx.z;

    int zLength = (gridDim.z * blockDim.z);
    int xzLength = (gridDim.y * blockDim.y) * zLength;

    int index = xzLength * x + zLength * y + z;

    if(blocks->BlockType != 200) {
        return;
    }
}

__device__ void checkNeighbour(Block *blocks, Blocks* newBlocks, int x, int y, int z) {

    int zLength = (gridDim.z * blockDim.z);
    int xzLength = (gridDim.y * blockDim.y) * zLength;

    // X
    if(x - 1 > 0) {
        if (blocks[xzLength * (x - 1) + zLength * y + z])->BlockType > 1 && blocks[xzLength * (x - 1) + zLength * y + z]->BlockState == 1) {
            if (blocks[] == 200) {
                newBlocks[xzLength * (x - 1) + zLength * y + z]->BlockAdditional = blocks[xzLength * (x - 1) + zLength * y + z]->BlockAdditional - 1;
            } else {
                newBlocks[xzLength * (x - 1) + zLength * y + z]->BlockAdditional = 15;
            }
        }
    }

    if(x + 1 < gridDim.x * blockDim.x) {
        if (blocks[xzLength * (x + 1) + zLength * y + z])->BlockType > 1 && blocks[xzLength * (x + 1) + zLength * y + z]->BlockState == 1) {
            if (blocks[] == 200) {
                newBlocks[xzLength * (x + 1) + zLength * y + z]->BlockAdditional = blocks[xzLength * (x + 1) + zLength * y + z]->BlockAdditional - 1;
            } else {
                newBlocks[xzLength * (x + 1) + zLength * y + z]->BlockAdditional = 15;
            }
        }
    }


    // Y
    if(y - 1 > 0) {
        if (blocks[xzLength * x + zLength * (y - 1) + z])->BlockType > 1 && blocks[xzLength * x + zLength * (y - 1) + z]->BlockState == 1) {
            if (blocks[] == 200) {
                newBlocks[xzLength * x + zLength * (y - 1) + z]->BlockAdditional = blocks[xzLength * x + zLength * (y - 1) + z]->BlockAdditional - 1;
            } else {
                newBlocks[xzLength * x + zLength * (y - 1) + z]->BlockAdditional = 15;
            }
        }
    }

    if(y + 1 < gridDim.y * blockDim.y) {
        if (blocks[xzLength * x + zLength * (y + 1) + z])->BlockType > 1 && blocks[xzLength * x + zLength * (y + 1) + z]->BlockState == 1) {
            if (blocks[] == 200) {
                newBlocks[xzLength * x + zLength * (y + 1) + z]->BlockAdditional = blocks[xzLength * x + zLength * (y + 1) + z]->BlockAdditional - 1;
            } else {
                newBlocks[xzLength * x + zLength * (y + 1) + z]->BlockAdditional = 15;
            }
        }
    }


    // Z
    if(z - 1 > 0) {
        if (blocks[xzLength * x + zLength * y + (z - 1)])->BlockType > 1 && blocks[xzLength * x + zLength * y + (z - 1)]->BlockState == 1) {
            if (blocks[] == 200) {
                newBlocks[xzLength * x + zLength * y + (z - 1)]->BlockAdditional = blocks[xzLength * x + zLength * y + (z - 1)]->BlockAdditional - 1;
            } else {
                newBlocks[xzLength * x + zLength * y + (z - 1)]->BlockAdditional = 15;
            }
        }
    }

    if(z + 1 < gridDim.z * blockDim.z) {
        if (blocks[xzLength * x + zLength * y + (z + 1)])->BlockType > 1 && blocks[xzLength * x + zLength * y + (z + 1)]->BlockState == 1) {
            if (blocks[] == 200) {
                newBlocks[xzLength * x + zLength * y + (z + 1)]->BlockAdditional = blocks[xzLength * x + zLength * y + (z + 1)]->BlockAdditional - 1;
            } else {
                newBlocks[xzLength * x + zLength * y + (z + 1)]->BlockAdditional = 15;
            }
        }
}