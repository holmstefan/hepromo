/**
 * Classes in this package provide simplified access to the HeProMo models.
 * All methods in this package use <code>double</code> as the only type for 
 * their method parameters. This should simplify the access from code written 
 * in other languages. Some method parameters represent categorical variables. 
 * In this case, the <code>double</code> value given as method parameter is 
 * first rounded to the closest <code>int</code> and then mapped to the 
 * categorical variable as described in the javadoc of the given method. Such 
 * methods have a method name with the suffix <code>_Kategorie</code>.
 * 
 * <br><br>
 * The models are initialised with (reasonable) default values, therefore all 
 * getters return a value even if none of the setters have been called before.
 * This means that models can often be used with only a few variables 
 * explicitly set, e.g. the amount of wood and the diameter at breast height.
 * The default values are stored inside public constants in the model class.
 * The model classes use lazy evaluation, i.e. no value is calculated until a 
 * getter is called.
 * 
 * <br><br>
 * If multiple calculations are carried out in a row, the following approach is
 * recommended:
 * <ol>
 * <li>Instantiate the model.</li>
 * <li>Set the input variables that are constant in your calculations, e.g. 
 * cost unit rates.</li>
 * <li>Set the input variables that are variable in your calculations, e.g. 
 * amount of wood.</li>
 * <li>Get the output variables that you are interested in.</li>
 * <li>Repeat step 3 and 4 for subsequent calculations.</li>
 * </ol>
 * With this approach, input variables that do not change have to be set only 
 * once, and the model instance can be re-used.
 * 
 * <br><br>
 * Class names have the following naming scheme: 
 * <code>Simple&lt;ModelName&gt;&lt;ModelYear&gt;</code> 
 * (e.g. SimpleForwarder2018).
 * 
 * <br><br>
 * Many models expect input in m3 over bark (in Rinde), while the output is
 * under bark (ohne Rinde). Please carefully check the method names, as they
 * indicate what they require/return: The suffix m3_iR stands for over bark, 
 * m3_oR for under bark.
 * 
 * <br><br>
 * Note that the classes in this package are not thread-safe. If calculations are
 * made in multiple threads, each thread must use its own model instance, or the 
 * access to the model instance needs to be synchronized appropriately.
 * <br><br>
 * 
 * 
 * @author Stefan Holm
 */
package ch.wsl.fps.hepromo.api;