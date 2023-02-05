package net.maystudios.mayredstone.compiler.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CompilerConfig {


    public int[] solid;
    public int[] transparent;


    public void readConfig(String file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ConfigDataBlocks data = mapper.readValue(new File(file), ConfigDataBlocks.class);
            solid = data.getSolid();
            transparent = data.getTransparent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ConfigDataBlocks {
        private int[] solid;
        private int[] transparent;

        public int[] getSolid() {
            return solid;
        }

        public void setSolid(int[] solid) {
            this.solid = solid;
        }

        public int[] getTransparent() {
            return transparent;
        }

        public void setTransparent(int[] transparent) {
            this.transparent = transparent;
        }
    }

}
