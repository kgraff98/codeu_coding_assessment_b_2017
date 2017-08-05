// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.mathlang.impl;

import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

import com.google.codeu.mathlang.core.tokens.NumberToken;
import com.google.codeu.mathlang.core.tokens.SymbolToken;
import com.google.codeu.mathlang.core.tokens.NameToken;
import com.google.codeu.mathlang.core.tokens.Token;
import com.google.codeu.mathlang.parsing.TokenReader;

// MY TOKEN READER
//
// This is YOUR implementation of the token reader interface. To know how
// it should work, read src/com/google/codeu/mathlang/parsing/TokenReader.java.
// You should not need to change any other files to get your token reader to
// work with the test of the system.
public final class MyTokenReader implements TokenReader {

  private String source;
  private ArrayList<Character> chars;
  private Queue<Token> tokens;

  public MyTokenReader(String source) {
    // Your token reader will only be given a string for input. The string will
    // contain the whole source (0 or more lines).
    this.source = source;
    this.chars = new ArrayList<Character>();
    this.tokens = new LinkedList<>();
  }

  @Override
  public Token next() throws IOException {
    // Most of your work will take place here. For every call to |next| you should
    // return a token until you reach the end. When there are no more tokens, you
    // should return |null| to signal the end of input.

    // If for any reason you detect an error in the input, you may throw an IOException
    // which will stop all execution.
    for (char c : source.toCharArray()) {
      chars.add(c);
    }
    String nameToken = "";
    String numberToken = "";
    String symbolToken = "";
    String stringToken = "";
    for(int x = 0; x < chars.size()-1; x++) {
      if(Character.isLetter(chars.get(x))) {
        nameToken = nameToken + chars.get(x);
      } else if (!(nameToken.equals(""))) {
        NameToken nameToke = new NameToken(nameToken);
        tokens.add(nameToke);
        nameToken = "";
      }
      if (Character.isDigit(chars.get(x))) {
        numberToken = numberToken + chars.get(x);
      } else if (!(numberToken.equals(""))) {
        double value = Double.parseDouble(numberToken);
        NumberToken numToke = new NumberToken(value);
        tokens.add(numToke);
        numberToken = "";
      }
      if (Character.isLetterOrDigit(chars.get(x)) == false){
        symbolToken = symbolToken + chars.get(x);
      } else if (!(symbolToken.equals(""))) {
        char c = symbolToken.charAt(0);
        SymbolToken symToke = new SymbolToken(c);
        tokens.add(symToke);
        symbolToken = "";
      }
    }
    return tokens.peek();
  }
}
