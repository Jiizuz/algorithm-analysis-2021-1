package com.github.jiizuz.algorithmanalysis.algorithm.riddle.tableriddle;

import com.github.jiizuz.algorithmanalysis.benchmark.ArrayTimeResults;
import com.github.jiizuz.algorithmanalysis.benchmark.ResultsUtils;
import com.github.jiizuz.algorithmanalysis.benchmark.TimeResults;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Main entry point of the riddle table algorithm.
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @since 1.6
 */
@UtilityClass
public class TableRiddleMain {

    /**
     * Main entry point of the program.
     *
     * @param args specified in the terminal
     */
    public void main(final String[] args) {
        final TableRiddle tableRiddle = new TableRiddle();

        final List<TableRiddle.Person> people = getDefaultPeople();
        final TimeResults timeResults = new ArrayTimeResults(16_777_216);

        tableRiddle.solve(people, timeResults, new Random());

        final StringBuilder bob = new StringBuilder();

        appendTable(people, bob);
        ResultsUtils.appendResults(timeResults, bob);

        System.out.print(bob);
    }

    // result util

    /**
     * Appends to the specified {@link StringBuilder} a user-display table with the
     * people in the specified {@link List} to display the results of the algorithm.
     *
     * <p>The list must follow the rules of:<pre>
     *     1 Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs.
     * </pre>
     *
     * <p>The result will look like (real algorithm result):<pre>
     *
     *            Aurelia Acalá       Alfonso Martínez    Analía Castaño
     *            Acuarelista         Aduanero            Apicultora
     *                      ------------------------------
     *                     /                              \
     *     Ambrosio Gómez |                                | Alberto Acalá
     *     Administrador  |                                | Abogado
     *                     \                              /
     *                      ------------------------------
     *            Angela Martínez     Alfredo Castaño     Alicia Gómez
     *            Actriz              Acupuntor           Escritora
     *
     * </pre>
     *
     * @param people list of people to generate with the result
     * @param bob    to append over the result
     * @param <T>    type of {@link TableRiddle.Person}
     * @throws IllegalArgumentException if the list does not have a size of {@code 8}
     */
    public <T extends TableRiddle.Person> void appendTable(final @NonNull List<T> people, final @NonNull StringBuilder bob) {
        checkArgument(people.size() == 8, "Invalid people list");

        final int nameLength = (people.get(6).name.toString() + people.get(6).lastName).length() + 1;
        final String leftPersonPad = Strings.repeat(" ", nameLength / 2);
        final String leftTablePad = Strings.repeat(" ", nameLength);

        bob.append('\n').append(String.format(
                        leftPersonPad + "%-16s    %-16s    %-16s\n"
                                + leftPersonPad + "%-16s    %-16s    %-16s\n"
                                + leftTablePad + "   ------------------------------\n"
                                + leftTablePad + "  /                              \\\n"
                                + "%s |                                | %-16s\n"
                                + "%-" + nameLength + "s |                                | %-16s\n"
                                + leftTablePad + "  \\                              /\n"
                                + leftTablePad + "   ------------------------------\n"
                                + leftPersonPad + "%-16s    %-16s    %-16s\n"
                                + leftPersonPad + "%-16s    %-16s    %-16s\n",
                        people.get(7).name + " " + people.get(7).lastName, people.get(0).name + " " + people.get(0).lastName, people.get(1).name + " " + people.get(1).lastName,
                        people.get(7).job, people.get(0).job, people.get(1).job,
                        people.get(6).name + " " + people.get(6).lastName, people.get(2).name + " " + people.get(2).lastName,
                        people.get(6).job, people.get(2).job,
                        people.get(5).name + " " + people.get(5).lastName, people.get(4).name + " " + people.get(4).lastName, people.get(3).name + " " + people.get(3).lastName,
                        people.get(5).job, people.get(4).job, people.get(3).job))
                .append('\n');
    }

    // util

    /**
     * Generates a brand-new {@link List} with exactly {@code 8} {@link TableRiddle.Person}
     * instances ready to be used in the algorithm.
     *
     * @return a new {@link List} with the {@code 8} instances
     * @apiNote The returned {@link List} will be {@link java.util.RandomAccess}.
     */
    @NonNull
    public List<TableRiddle.Person> getDefaultPeople() {
        final List<TableRiddle.Person> people = Lists.newArrayListWithCapacity(8);
        fill(people, TableRiddle.Person::new);
        return people;
    }

    /**
     * Fills the specified list with exactly {@code 8} {@link TableRiddle.Person}
     * instances provided by the specified {@link Supplier}.
     *
     * @param people   to fill with the instances
     * @param supplier to generate branch new instances on-demand
     * @throws IllegalArgumentException if the list is not empty already
     */
    public <T extends TableRiddle.Person> void fill(final @NonNull List<T> people, final @NonNull Supplier<T> supplier) {
        checkArgument(people.isEmpty(), "provided non-empty list");

        for (byte i = 0, n = 8; i < n; ++i) {
            people.add(supplier.get());
        }
    }
}
