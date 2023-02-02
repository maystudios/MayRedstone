extern "C"
__global__ void sampleKernel(int *a, int *b, int *c)
{
    int i = blockIdx.x * blockDim.x + threadIdx.x;
    if (i < 2)
    {
        c[i] = a[i] + b[i];
    }
}