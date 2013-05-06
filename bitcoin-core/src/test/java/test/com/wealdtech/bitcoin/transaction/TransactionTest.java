/*
 *    Copyright 2013 Weald Technology Trading Limited
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package test.com.wealdtech.bitcoin.transaction;

import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import com.wealdtech.DataError;

public class TransactionTest
{
  @Test
  public void testParseValid1() throws Exception
  {
  }

  @Test
  public void testParseInvalid1() throws Exception
  {
    try
    {
      fail("");
    }
    catch (DataError.Bad de)
    {
      // Good
    }
  }

}
