/*
 * Copyright (c) 2015. All rights reserved.
 */
package encv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * File Tools
 * @author DBJ(dubenju@126.com)
 *
 */
public class UFile {

    /**
     * Constructor
     */
    private UFile() {
    }

    /**
     * File Tools
     * @param fullPath
     * @return
     */
    public static boolean isFile(String fullPath) {
        boolean bResult = true;
        if (fullPath == null) {
            bResult = false;
        }
        if (bResult) {
            File file = new File(fullPath);
            if (file.isFile() == true && file.exists() ==true && file.canRead() == true) {
            } else {
                bResult = false;
            }
        }
        return bResult;
    }

    /**
     * Reading file
     * @param input
     * @param decode
     * @return
     * @throws IOException
     */
    public static String readFile(String input, String decode) throws IOException {
        File file = new File(input);
        FileInputStream inputStream = null;
        String result = null;

        try {
            inputStream = new FileInputStream(file);
            // long size = file.length();
            int size = inputStream.available();
            // TODO:int overflow
            byte[] inputbs = new byte[size];
            inputStream.read(inputbs);
            result = new String(inputbs, decode);
        } catch(IOException ex) {
            throw ex;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                inputStream = null;
            }
        }
        return result;
    }

    /**
     * Encoding conversion
     * @param input
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encn(String input, String encode) throws UnsupportedEncodingException {
        String result = null;
        if (input == null) {
            return result;
        }
        result = new String(input.getBytes(encode), encode);

        return result;
    }

    /**
     * Write file
     * @param file
     * @param out
     * @param encode
     * @throws IOException
     */
    public static void writeFile(String file, String out, String encode) throws IOException {
        File outfile = new File(file);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outfile);
            byte[] bs = out.getBytes(encode);
            outputStream.write(bs);
            outputStream.flush();
        } catch(IOException ex) {
            throw ex;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                outputStream = null;
            }
        }
    }
}
