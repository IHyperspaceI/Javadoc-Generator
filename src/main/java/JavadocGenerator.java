public class JavadocGenerator {

    public String generateJavadocContent(String javaCode) {
        if (javaCode.contains("class ")) {
            return generateClassJavadoc(javaCode);
        } else if (javaCode.contains("(") && javaCode.contains(")")) {
            if (javaCode.startsWith("public ") || javaCode.startsWith("private ") || javaCode.startsWith("protected ")) {
                if (javaCode.contains("{")) {
                    return generateMethodJavadoc(javaCode);
                } else {
                    return generateConstructorJavadoc(javaCode);
                }
            }
        }
        return "Invalid Java code.";
    }

    private String generateClassJavadoc(String classDefinition) {
        StringBuilder javadoc = new StringBuilder("/**\n");
        String[] lines = classDefinition.split("\\s+");

        String className = "";
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].equals("class")) {
                className = lines[i + 1];
                break;
            }
        }

        javadoc.append(" * Represents the ").append(className).append(" class.\n");
        // javadoc.append(" * <p>\n");
        javadoc.append(" * Add a brief description of the class here.\n");
        // javadoc.append(" * </p>\n");
        javadoc.append(" */");
        return javadoc.toString();
    }

    private String generateMethodJavadoc(String method) {
        StringBuilder javadoc = new StringBuilder("/**\n");
        String[] lines = method.split("\\(");
        String methodHeader = lines[0].trim();

        String[] headerParts = methodHeader.split("\\s+");
        String returnType = headerParts[headerParts.length - 2];
        String methodName = headerParts[headerParts.length - 1];

        String parameterPart = lines[1].split("\\)")[0].trim();
        String[] parameters = parameterPart.isEmpty() ? new String[0] : parameterPart.split(",");

        if (methodName.equals("toString") && returnType.equals("String") && parameters.length == 0) {
            javadoc.append(" * Returns a string representation of the object.\n");
            javadoc.append(" * @return String representation of the object.\n");
        } else if (methodName.startsWith("get") && parameters.length == 0) {
            javadoc.append(" * Gets the ").append(methodName.substring(3).toLowerCase()).append(".\n");
            javadoc.append(" * @return ").append(returnType).append(" the ").append(methodName.substring(3).toLowerCase()).append(".\n");
        } else if (methodName.startsWith("set") && parameters.length == 1) {
            javadoc.append(" * Sets the ").append(methodName.substring(3).toLowerCase()).append(".\n");
            javadoc.append(" * @param ").append(parameters[0].split("\\s+")[1]).append(" the new value.\n");
        } else {
            javadoc.append(" * ").append(methodName).append(" method.\n");
            for (String param : parameters) {
                String[] paramParts = param.trim().split("\\s+");
                javadoc.append(" * @param ").append(paramParts[1]).append(" the ").append(paramParts[1]).append(".\n");
            }
            if (!returnType.equals("void")) {
                javadoc.append(" * @return ").append(returnType).append(" result of the method.\n");
            }
        }

        javadoc.append(" */");
        return javadoc.toString();
    }

    private String generateConstructorJavadoc(String constructor) {
        StringBuilder javadoc = new StringBuilder("/**\n");
        String[] lines = constructor.split("\\(");
        String constructorHeader = lines[0].trim();

        String constructorName = constructorHeader.split("\\s+")[1];

        String parameterPart = lines[1].split("\\)")[0].trim();
        String[] parameters = parameterPart.isEmpty() ? new String[0] : parameterPart.split(",");

        javadoc.append(" * Constructor for ").append(constructorName).append(".\n");
        for (String param : parameters) {
            String[] paramParts = param.trim().split("\\s+");
            javadoc.append(" * @param ").append(paramParts[1]).append(" the ").append(paramParts[1]).append(".\n");
        }

        javadoc.append(" */");
        return javadoc.toString();
    }
}
