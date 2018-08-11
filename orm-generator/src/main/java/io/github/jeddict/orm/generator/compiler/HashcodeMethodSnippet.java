/**
 * Copyright 2013-2018 the original author or authors from the Jeddict project (https://jeddict.github.io/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.github.jeddict.orm.generator.compiler;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import io.github.jeddict.jpa.spec.DefaultAttribute;
import io.github.jeddict.jpa.spec.extend.Attribute;
import io.github.jeddict.jpa.spec.extend.BaseAttribute;
import io.github.jeddict.jpa.spec.extend.ClassMembers;
import io.github.jeddict.jpa.spec.extend.CompositionAttribute;
import static io.github.jeddict.orm.generator.compiler.JavaHashcodeEqualsUtil.getHashcodeExpression;
import static io.github.jeddict.orm.generator.util.ORMConverterUtil.NEW_LINE;

public class HashcodeMethodSnippet implements Snippet {

    private final String className;
    private final ClassMembers classMembers;
    
    private boolean objectsImport;
    private String hashcodeMethodSnippet;

    public HashcodeMethodSnippet(String className, ClassMembers classMembers) {
        this.className = className;
        this.classMembers = classMembers;
    }

    @Override
    public String getSnippet() throws InvalidDataException {
        if(hashcodeMethodSnippet == null){
            hashcodeMethodSnippet = getHashcodeMethodSnippet();
        }
        return hashcodeMethodSnippet;
    }
        
    public String getHashcodeMethodSnippet() throws InvalidDataException {
        
        StringBuilder builder = new StringBuilder();
        int startNumber = 7;//generatePrimeNumber(2, 10);
        int multiplyNumber = 31;//generatePrimeNumber(10, 100);

        if(!classMembers.getAttributes().isEmpty()){
            builder.append(String.format("int hash = %s;",startNumber)).append(NEW_LINE);
        }
        
        if (StringUtils.isNotBlank(classMembers.getPreCode())) {
            builder.append(classMembers.getPreCode()).append(NEW_LINE);
        }
        
        for (int i = 0; i < classMembers.getAttributes().size(); i++) {
            Attribute attribute = classMembers.getAttributes().get(i);
            if(attribute instanceof DefaultAttribute) {
                attribute = ((DefaultAttribute)attribute).getConnectedAttribute();
            }
            
            String expression;
            boolean optionalType = attribute.isOptionalReturnType();
            if(attribute instanceof BaseAttribute && !(attribute instanceof CompositionAttribute)){
                expression = getHashcodeExpression(((BaseAttribute)attribute).getAttributeType(), attribute.getName(), optionalType);
            } else {
                expression = getHashcodeExpression(attribute.getName(), optionalType);
            }
            builder.append("        ")
                   .append(String.format("hash = %s * hash + %s;", multiplyNumber, expression)).append(NEW_LINE);
        }
        
        if (StringUtils.isNotBlank(classMembers.getPostCode())) {
            builder.append(classMembers.getPostCode()).append(NEW_LINE);
        }
        
        if(!classMembers.getAttributes().isEmpty()){
            builder.append("        ")
                   .append("return hash;");
        }
        String result = builder.toString();
        objectsImport = result.contains("Objects");
        return result;
    }

    @Override
    public Collection<String> getImportSnippets() throws InvalidDataException {
        if(hashcodeMethodSnippet == null){
            hashcodeMethodSnippet = getHashcodeMethodSnippet();
        }
        return objectsImport ? Collections.singletonList("java.util.Objects") : Collections.<String>emptyList();
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    private static boolean isPrimeNumber(int n) {
        int squareRoot = (int) Math.sqrt(n) + 1;
        if (n % 2 == 0) {
            return false;
        }
        for (int cntr = 3; cntr < squareRoot; cntr++) {
            if (n % cntr == 0) {
                return false;
            }
        }
        return true;
    }

    static int randomNumber = -1;

    private static int generatePrimeNumber(int lowerLimit, int higherLimit) {
        if (randomNumber > 0) {
            return randomNumber;
        }

        Random r = new Random(System.currentTimeMillis());
        int proposed = r.nextInt(higherLimit - lowerLimit) + lowerLimit;
        while (!isPrimeNumber(proposed)) {
            proposed++;
        }
        if (proposed > higherLimit) {
            proposed--;
            while (!isPrimeNumber(proposed)) {
                proposed--;
            }
        }
        return proposed;
    }

    /**
     * @return the classMembers
     */
    public ClassMembers getClassMembers() {
        return classMembers;
    }
}
