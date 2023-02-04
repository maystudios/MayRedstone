extern "C"

__device__ void checkNeighbour(int x, int y, int z, int *blockType, int *blockState, int *blockAdditional, int *blockAdvanced, int *blockDirection,
                               int *newBlockType, int *newBlockState, int *newBlockAdditional, int*newBlockAdvanced, int *newBlockDirection) {

    int zLength = (gridDim.z * blockDim.z);
    int xzLength = (gridDim.y * blockDim.y) * zLength;

    // X
    if(x - 1 > 0) {
        if (blockType[xzLength * (x - 1) + zLength * y + z] > 1 && blockState[xzLength * (x - 1) + zLength * y + z] == 1) {
            if (blockType[xzLength * (x - 1) + zLength * y + z] == 200) {
                newBlockAdditional[xzLength * (x - 1) + zLength * y + z] = newBlockAdditional[xzLength * (x - 1) + zLength * y + z] - 1;
            } else {
                newBlockAdditional[xzLength * (x - 1) + zLength * y + z] = 15;
            }
        }
    }

    if(x + 1 < gridDim.x * blockDim.x) {
        if (blockType[xzLength * (x + 1) + zLength * y + z] > 1 && blockState[xzLength * (x + 1) + zLength * y + z] == 1) {
            if (blockType[xzLength * (x + 1) + zLength * y + z] == 200) {
                newBlockAdditional[xzLength * (x + 1) + zLength * y + z] = newBlockAdditional[xzLength * (x + 1) + zLength * y + z] - 1;
            } else {
                newBlockAdditional[xzLength * (x + 1) + zLength * y + z] = 15;
            }
        }
    }


    // Y
    if(y - 1 > 0) {
        if (blockType[xzLength * x + zLength * (y - 1) + z] > 1 && blockState[xzLength * x + zLength * (y - 1) + z] == 1) {
            if (blockType[xzLength * x + zLength * (y - 1) + z] == 200) {
                newBlockAdditional[xzLength * x + zLength * (y - 1) + z] = newBlockAdditional[xzLength * x + zLength * (y - 1) + z] - 1;
            } else {
                newBlockAdditional[xzLength * x + zLength * (y - 1) + z] = 15;
            }
        }
    }

    if(y + 1 < gridDim.y * blockDim.y) {
        if (blockType[xzLength * x + zLength * (y + 1) + z] > 1 && blockState[xzLength * x + zLength * (y + 1) + z] == 1) {
            if (blockType[xzLength * x + zLength * (y + 1) + z] == 200) {
                newBlockAdditional[xzLength * x + zLength * (y + 1) + z] = newBlockAdditional[xzLength * x + zLength * (y + 1) + z] - 1;
            } else {
                newBlockAdditional[xzLength * x + zLength * (y + 1) + z] = 15;
            }
        }
    }


    // Z
    if(z - 1 > 0) {
        if (blockType[xzLength * x + zLength * y + (z - 1)] > 1 && blockState[xzLength * x + zLength * y + (z - 1)] == 1) {
            if (blockType[xzLength * x + zLength * y + (z - 1)] == 200) {
                newBlockAdditional[xzLength * x + zLength * y + (z - 1)] = newBlockAdditional[xzLength * x + zLength * y + (z - 1)] - 1;
            } else {
                newBlockAdditional[xzLength * x + zLength * y + (z - 1)] = 15;
            }
        }
    }

    if(z + 1 < gridDim.z * blockDim.z) {
        if (blockType[xzLength * x + zLength * y + (z + 1)] > 1 && blockState[xzLength * x + zLength * y + (z + 1)] == 1) {
            if (blockType[xzLength * x + zLength * y + (z + 1)] == 200) {
                newBlockAdditional[xzLength * x + zLength * y + (z + 1)] = newBlockAdditional[xzLength * x + zLength * y + (z + 1)] - 1;
            } else {
                newBlockAdditional[xzLength * x + zLength * y + (z + 1)] = 15;
            }
        }
    }
}

__global__ void RedstoneCompiler_Wiring(int *blockType, int *blockState, int *blockAdditional, int *blockAdvanced, int *blockDirection,
                                        int *newBlockType, int *newBlockState, int *newBlockAdditional, int*newBlockAdvanced, int *newBlockDirection) {
    int x = blockIdx.x * blockDim.x + threadIdx.x;
    int y = blockIdx.y * blockDim.y + threadIdx.y;
    int z = blockIdx.z * blockDim.z + threadIdx.z;

    int zLength = (gridDim.z * blockDim.z);
    int xzLength = (gridDim.y * blockDim.y) * zLength;

    if(blockType[xzLength * x + zLength * y + z] != 200) {
        return;
    }

    checkNeighbour(x, y, z, blockType, blockState, blockAdditional, blockAdvanced, blockDirection, newBlockType, newBlockState, newBlockAdditional, newBlockAdvanced, newBlockDirection);
}

