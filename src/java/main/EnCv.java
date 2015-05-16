/*
 * Copyright (c) 2015. All rights reserved.
 */
package main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import encv.UFile;

/**
 * Encoding conversion
 * @author DBJ(dubenju@126.com)
 *
 */
public class EnCv {

    /**
     * Constructor
     */
    public EnCv() {
    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }

        EnCv proc = new EnCv();
        int cd = proc.parseCLI(args);

        System.out.println("end(" + cd + ").");
    }

    protected void showUsage(Options opts) {
        HelpFormatter hf = new HelpFormatter();
        hf.printHelp(
            "encv <-i inputfile> <-d inputfile's encode> <-o output file> <-e outputfile's encode> [-h]\n" +
            "       input file's encoding conversion save as output file.\n" +
            "       encv version 0.0.1(2015/05/16).\n" +
            "options:\n", opts);
    }

    /**
     * parse command line
     * @param args
     */
    public int parseCLI(String[] args) {
        String input = null;
        String decode = null;
        String output = null;
        String encode = null;

        String in = null;
        String out = null;

        // get option
        Options opts = new Options();
        opts.addOption("i", "input", true, "the input file for conver.");
        opts.addOption("d", "decode", true, "the input file's encode.");
        opts.addOption("o", "output", true, "the output file.");
        opts.addOption("e", "encode", true, "the output file's encode.");
        opts.addOption("h", "help", false, "show usage for this.");

        CommandLineParser parser = new DefaultParser();
        CommandLine cl = null;
        try {
            cl = parser.parse(opts, args);
            if (cl.getOptions().length > 0) {
                if (cl.hasOption('h')) {
                    showUsage(opts);

                    return -1;
                } else {
                    input = cl.getOptionValue("i");
                    decode = cl.getOptionValue("d");
                    output = cl.getOptionValue("o");
                    encode = cl.getOptionValue("e");

                    System.out.println(decode + ":" + input + " to " + encode + ":" + output);
                }
            } else {
                showUsage(opts);
                return -2;
            }
        } catch (ParseException e) {
            showUsage(opts);
            e.printStackTrace();
            return -3;
        }

        // check
        if (UFile.isFile(input) == false) {
            showUsage(opts);
            return -4;
        }

        // read
        try {
            in = UFile.readFile(input, decode);
        } catch (IOException e) {
            showUsage(opts);
            e.printStackTrace();
            return -5;
        }

        System.out.println("in=" + in);
        // conver
        if (in != null) {
            try {
                out = UFile.encn(in, encode);
            } catch (UnsupportedEncodingException e) {
                showUsage(opts);
                e.printStackTrace();
                return -6;
            }
            in = null;
        }

        System.out.println("out=" + out);

        // write
        if (out != null) {
            try {
                UFile.writeFile(output, out, encode);
            } catch (IOException e) {
                showUsage(opts);
                e.printStackTrace();
                return -7;
            }
            out = null;
        }
        return 0;
    }
}
