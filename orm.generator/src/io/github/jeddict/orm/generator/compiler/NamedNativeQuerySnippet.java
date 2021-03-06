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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static io.github.jeddict.jcode.jpa.JPAConstants.NAMED_NATIVE_QUERY;
import static io.github.jeddict.jcode.jpa.JPAConstants.NAMED_NATIVE_QUERY_FQN;
import io.github.jeddict.orm.generator.util.ClassHelper;
import io.github.jeddict.orm.generator.util.ORMConverterUtil;

public class NamedNativeQuerySnippet extends NamedQuerySnippet implements Snippet {

    private ClassHelper classHelper = new ClassHelper();
    private String resultSetMapping = null;

    public String getResultClass() {
        return classHelper.getClassNameWithClassSuffix();
    }

    public void setResultClass(String resultClass) {
        classHelper.setClassName(resultClass);
    }

    public void setPackageName(String packageName) {
        classHelper.setPackageName(packageName);
    }

    public String getResultSetMapping() {
        return resultSetMapping;
    }

    public void setResultSetMapping(String resultSetMapping) {
        this.resultSetMapping = resultSetMapping;
    }

    @Override
    public String getSnippet() throws InvalidDataException {

        if (name == null || query == null) {
            throw new InvalidDataException(
                    "Query data missing, Name:" + name + " Query: " + query);
        }

        //remove new lines & tabs from query
        query = query.replaceAll("\\n", " ");
        query = query.replaceAll("\\t", " ");

        StringBuilder builder = new StringBuilder();

        builder.append("@").append(NAMED_NATIVE_QUERY).append("(name=\"");
        builder.append(name);
        builder.append(ORMConverterUtil.QUOTE);
        builder.append(ORMConverterUtil.COMMA);

        builder.append("query=\"");
        builder.append(query);
        builder.append(ORMConverterUtil.QUOTE);
        builder.append(ORMConverterUtil.COMMA);

        if (classHelper.getClassName() != null) {
            builder.append("resultClass=");
            builder.append(getResultClass());
            builder.append(ORMConverterUtil.COMMA);
        }

        if (resultSetMapping != null) {
            builder.append("resultSetMapping=\"");
            builder.append(resultSetMapping);
            builder.append(ORMConverterUtil.QUOTE);
            builder.append(ORMConverterUtil.COMMA);
        }

        if (!queryHints.isEmpty()) {
            builder.append("hints={");

            for (QueryHintSnippet queryHint : queryHints) {
                builder.append(queryHint.getSnippet());
                builder.append(ORMConverterUtil.COMMA);
            }

            builder.deleteCharAt(builder.length() - 1);

            builder.append(ORMConverterUtil.CLOSE_BRACES);
            builder.append(ORMConverterUtil.COMMA);
        }

        return builder.substring(0, builder.length() - 1)
                + ORMConverterUtil.CLOSE_PARANTHESES;
    }

    @Override
    public Collection<String> getImportSnippets() throws InvalidDataException {
        List<String> importSnippets = new ArrayList<>();

        importSnippets.add(NAMED_NATIVE_QUERY_FQN);
        if (classHelper.getClassName() != null) {
            importSnippets.add(classHelper.getFQClassName());
        }
        if (!queryHints.isEmpty()) {
        importSnippets.addAll(queryHints.get(0).getImportSnippets());
        }

        return importSnippets;
    }
}
