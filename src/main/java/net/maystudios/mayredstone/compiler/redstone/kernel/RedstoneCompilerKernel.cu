extern "C"
__global__ void RedstoneCompiler(int *Blocks, int *BlockStates, int *newBlockStates) {
    int x = blockIdx.x * blockDim.x + threadIdx.x;
    int y = blockIdx.y * blockDim.y + threadIdx.y;
    int z = blockIdx.z * blockDim.z + threadIdx.z;


}

__device__ void (int x, int y , int z, int *Blocks, int *BlockStates, int *newBlockStates) {

}

__global__ void RedstoneDustCompiler(int *Blocks, int *BlockStates, int *newBlockStates, int x, int y, int z) {
    int x = blockIdx.x * blockDim.x + threadIdx.x;
    int y = blockIdx.y * blockDim.y + threadIdx.y;
    int z = blockIdx.z * blockDim.z + threadIdx.z;

    int index = x * (gridDim.y * blockDim.y ) + y
}