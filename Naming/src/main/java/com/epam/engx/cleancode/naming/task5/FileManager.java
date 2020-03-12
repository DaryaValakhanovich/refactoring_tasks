package com.epam.engx.cleancode.naming.task5;

import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidDirectoryException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidFileTypeException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.PropertyUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class FileManager {

    private static final String[] IMAGES_TYPES = {"jpg", "png"};
    private static final String[] DOCUMENTS_TYPES = {"pdf", "doc"};

    private String basePath = PropertyUtil.loadProperty("basePath");

    public File retrieveFile(String fileName) {
        validateFileType(fileName);
        final String directoryPath = basePath + File.separator;
        return Paths.get(directoryPath, fileName).toFile();
    }

    public List<String> listAllImages() {
        return files(basePath, IMAGES_TYPES);
    }

    public List<String> listAllDocumentFiles() {
        return files(basePath, DOCUMENTS_TYPES);
    }

    private void validateFileType(String fileName) {
        if (isInvalidFileType(fileName)) {
            throw new InvalidFileTypeException("File type not Supported: " + fileName);
        }
    }

    private boolean isInvalidFileType(String fileName) {
        return isInvalidImage(fileName) && isInvalidDocument(fileName);
    }

    private boolean isInvalidImage(String fileName) {
        FileExtensionsPredicate imageExtensionsPredicate = new FileExtensionsPredicate(IMAGES_TYPES);
        return !imageExtensionsPredicate.test(fileName);
    }

    private boolean isInvalidDocument(String fileName) {
        FileExtensionsPredicate documentExtensionsPredicate = new FileExtensionsPredicate(DOCUMENTS_TYPES);
        return !documentExtensionsPredicate.test(fileName);
    }

    private List<String> files(String directoryPath, String[] allowedExtensions) {
        final FileExtensionsPredicate pred = new FileExtensionsPredicate(allowedExtensions);
        return Arrays.asList(getFileByDirectory(directoryPath).list(getFilenameFilterByPredicate(pred)));
    }

    private FilenameFilter getFilenameFilterByPredicate(final FileExtensionsPredicate predicate) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String str) {
                return predicate.test(str);
            }
        };
    }

    private File getFileByDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        validateDirectory(directory);
        return directory;
    }

    private void validateDirectory(File directory) {
        if (isNotDirectory(directory)) {
            throw new InvalidDirectoryException("Invalid directory found: " + directory.getAbsolutePath());
        }
    }

    private boolean isNotDirectory(File directory) {
        return !directory.isDirectory();
    }

}