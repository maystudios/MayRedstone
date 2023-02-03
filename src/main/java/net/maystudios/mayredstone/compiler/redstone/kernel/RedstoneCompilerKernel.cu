extern "C"

struct Block {
    int BlockType;
    int BlockState;
};

__global__ void RedstoneCompiler(Block *Blocks, Block *newBlockStates) {
    int x = blockIdx.x * blockDim.x + threadIdx.x;
    int y = blockIdx.y * blockDim.y + threadIdx.y;
    int z = blockIdx.z * blockDim.z + threadIdx.z;

    int zLength = (gridDim.z * blockDim.z);
    int xzLength = (gridDim.y * blockDim.y) * zLength;

    int index = xzLength * x + zLength * y + z;

    int _BlockType = Block[index]->BlockType;

    switch(BlockType) {
        case 0:

        break;

        case 1:

        break;
    }
}

__device__ void calc (int x, int y , int z, int *Blocks, int *BlockStates, int *newBlockStates) {

}



/*
__global__ void RedstoneDustCompiler(int *Blocks, int *BlockStates, int *newBlockStates, int x, int y, int z) {
    int x = blockIdx.x * blockDim.x + threadIdx.x;
    int y = blockIdx.y * blockDim.y + threadIdx.y;
    int z = blockIdx.z * blockDim.z + threadIdx.z;

    int index = 0;

    if (z - 1 >= 0) {
        
    }
}

__global__ void RedstonePoweredCompiler(CUdeviceptr devBlocks, int *newBlockStates) {

    int x = blockIdx.x * blockDim.x + threadIdx.x;
    int y = blockIdx.y * blockDim.y + threadIdx.y;
    int z = blockIdx.z * blockDim.z + threadIdx.z;

    // int index = (gridDim.y * blockDim.y) * (gridDim.z * blockDim.z) * x + (gridDim.z * blockDim.z) * y + z;

    int zLength = (gridDim.z * blockDim.z);
    int xzLength = (gridDim.y * blockDim.y) * zLength;

    int index = xzLength * x + zLength * y + z;

    Block particle = ((Block*)devBlocks)[index];

    switch ()
}
*/

