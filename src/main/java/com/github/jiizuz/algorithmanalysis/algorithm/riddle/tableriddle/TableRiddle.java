package com.github.jiizuz.algorithmanalysis.algorithm.riddle.tableriddle;

import com.github.jiizuz.algorithmanalysis.benchmark.TimeResults;
import com.google.common.collect.ImmutableList;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;

import static com.github.jiizuz.algorithmanalysis.algorithm.riddle.tableriddle.TableRiddle.Job.*;
import static com.github.jiizuz.algorithmanalysis.algorithm.riddle.tableriddle.TableRiddle.LastName.*;
import static com.github.jiizuz.algorithmanalysis.algorithm.riddle.tableriddle.TableRiddle.Name.*;
import static com.github.jiizuz.algorithmanalysis.algorithm.riddle.tableriddle.TableRiddle.Type.MR;
import static com.github.jiizuz.algorithmanalysis.algorithm.riddle.tableriddle.TableRiddle.Type.MRS;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * Algorithm to solve a combinatorial problem.
 *
 * <p>This riddle has been read from <a href="http://acertijosymascosas.com/acertijo-de-logica-tipo-einstein/">this</a>
 * post, and I tried to solve it, but it could take hours to find a
 * valid solution, so I developed this brute-force algorithm to find
 * a solution following the rules and arguments of the riddle.
 *
 * <p>I will copy the riddle, in case it gets down.
 *
 * <p>Cuatro parejas se disponen a cenar y se sientan en una mesa redonda y,
 * aunque cada hombre se sienta entre dos mujeres, ninguno lo hizo junto a
 * la suya. <b>Uno de los ocho es acupuntor</b>. ¿Podrás encontrar los nombres
 * completos de los maridos, los nombres propios de las mujeres respectivas,
 * las ocupaciones de los ocho comensales y el orden en que se sentaron a la
 * mesa?
 *
 * <br><br><i>Restrictions to accomplish:</i>
 *
 * <ol>
 *     <li>La señora Martinez  es actriz, Alberto es abogado.</li>
 *     <li>El señor Gomez se sentó entre Angela y la acuarelista.</li>
 *     <li>Ambrosio está casado con la escritora.</li>
 *     <li>Aurelia se sentó inmediatamente a la izquierda del administrador de Correos y directamente enfrente de la señora Gomez.</li>
 *     <li>El nombre propio de la señora Castaño es Analía.</li>
 *     <li>Alfonso se sentó entre la señora Alcalá y la apicultora.</li>
 *     <li>Alfredo ocupó el lugar directamente enfrente del aduanero.</li>
 *     <li>Alicia se sentó a la derecha del señor Castaño.</li>
 * </ol>
 *
 * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
 * @see <a href="http://acertijosymascosas.com/acertijo-de-logica-tipo-einstein/">Riddle</a>
 * @since 1.6
 */
public class TableRiddle {

    /**
     * {@link List} with the names of the sirs.
     */
    private static final List<Name> SIRS_NAMES = ImmutableList.of(ALBERTO, AMBROSIO, ALFONSO, ALFREDO);
    /**
     * {@link List} with the jobs of the sirs.
     */
    private static final List<Job> SIRS_JOBS = ImmutableList.of(ACUPUNCTURIST, LAWYER, POSTMASTER, CUSTOMS_OFFICIAL);

    /**
     * {@link List} with the names of the ladies.
     */
    private static final List<Name> LADIES_NAMES = ImmutableList.of(ANGELA, AURELIA, ALICIA, ANALIA);
    /**
     * {@link List} with the jobs of the ladies.
     */
    private static final List<Job> LADIES_JOBS = ImmutableList.of(ACTRESS, WATERCOLORIST, WRITER, BEEKEEPER);

    /**
     * {@link List} with the four last names.
     */
    private static final List<LastName> LAST_NAMES = ImmutableList.of(ACALA, GOMEZ, CASTANIO, MARTINEZ);

    /**
     * {@link List} with the restrictions to accomplish in order to have a successful combination.
     */
    private static final List<Predicate<List<? extends Person>>> RESTRICTIONS = ImmutableList.of(
            people -> filterByLastName(people, MARTINEZ, MRS).job == ACTRESS, // 1.1
            people -> filterByLastName(people, CASTANIO, MRS).name == ANALIA, // 5.
            TableRiddle::doesNotHavePartnersTogether, // general restriction
            people -> {
                final Person person = filterByLastName(people, GOMEZ, MR);
                return person.left.name == ANGELA && person.right.job == WATERCOLORIST
                        || person.left.job == WATERCOLORIST && person.right.name == ANGELA;
            }, // 2.
            people -> filterByName(people, ALBERTO).job == LAWYER, // 1.2
            people -> {
                final Person person = filterByName(people, AURELIA);
                return person.right.job == POSTMASTER && person.front.lastName == GOMEZ;
            }, // 4.
            people -> {
                final Person person = filterByName(people, ALFONSO);
                return person.left.lastName == ACALA && person.right.job == BEEKEEPER
                        || person.left.job == BEEKEEPER && person.right.lastName == ACALA;
            }, // 6.
            people -> filterByName(people, ALFREDO).front.job == CUSTOMS_OFFICIAL, // 7.
            people -> filterByName(people, ALICIA).left.lastName == CASTANIO, // 8.
            people -> filterByLastName(people, filterByName(people, AMBROSIO).lastName, MRS).job == WRITER); // 3.

    // algorithm

    /**
     * Finds a valid solution for the Riddle using Brute-Force.
     *
     * <p>The result will be stored in the {@link Person} instances that are
     * contained in the specified {@link List}. The list also will be sorted
     * based on the next format:<pre>
     *    1 Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs.
     * </pre>
     *
     * <p>As you can see, in the clockwise direction.
     *
     * <p>The timings of each attempt will be registered in the specified results.
     * (If the time results are {@code null}, no attempt to register will be made)
     *
     * <p>If a certain amount of attempts is made, the {@link Random} instance
     * will be discarded and another instance will be generated.
     *
     * @param <T>         type of {@link Person}
     * @param people      list with the {@link Person} instances to write the results
     * @param timeResults can be {@code null}. Used to track the attempt timings
     * @param random      instance to generate pseudo-random indexes
     * @throws NullPointerException     if either the people list or random are {@code null}
     * @throws IllegalArgumentException if the list does not have a size of {@code 8}
     */
    public <T extends Person> void solve(final @NonNull List<T> people, final TimeResults timeResults, @NonNull Random random) {
        checkArgument(people.size() == 8, "Invalid people list");

        int attempt = 0;

        boolean success;
        Person person;
        // mask for random retrieval
        byte nameIndex, lastNameIndex, jobIndex;
        byte sirNameMask, sirLastNameMask, sirJobMask;
        byte ladyNameMask, ladyLastNameMask, ladyJobMask;
        // time cache
        long t1, t2;

        do {
            if (attempt % 1_000_000 == 0) { // discreet status
                System.out.print("\rAttempt: " + attempt / 1_000_000 + ",000,000");
            }
            if (attempt++ == 100_000_000) { // try another seed
                random = new Random();
                attempt = 0;
            }

            // reset masks
            sirNameMask = sirLastNameMask = sirJobMask = 0;
            ladyNameMask = ladyLastNameMask = ladyJobMask = 0;

            t1 = System.nanoTime();

            for (byte i = 0; i < 8; i += 2) { // generate random combination
                do {
                    nameIndex = (byte) random.nextInt(4);
                } while ((sirNameMask & 1 << nameIndex) != 0);
                sirNameMask |= 1 << nameIndex;
                do {
                    lastNameIndex = (byte) random.nextInt(4);
                } while ((sirLastNameMask & 1 << lastNameIndex) != 0);
                sirLastNameMask |= 1 << lastNameIndex;
                do {
                    jobIndex = (byte) random.nextInt(4);
                } while ((sirJobMask & 1 << jobIndex) != 0);
                sirJobMask |= 1 << jobIndex;

                person = people.get(i);
                person.index = i;
                person.name = SIRS_NAMES.get(nameIndex);
                person.lastName = LAST_NAMES.get(lastNameIndex);
                person.job = SIRS_JOBS.get(jobIndex);
                person.type = MR;

                do {
                    nameIndex = (byte) random.nextInt(4);
                } while ((ladyNameMask & 1 << nameIndex) != 0);
                ladyNameMask |= 1 << nameIndex;
                do {
                    lastNameIndex = (byte) random.nextInt(4);
                } while ((ladyLastNameMask & 1 << lastNameIndex) != 0);
                ladyLastNameMask |= 1 << lastNameIndex;
                do {
                    jobIndex = (byte) random.nextInt(4);
                } while ((ladyJobMask & 1 << jobIndex) != 0);
                ladyJobMask |= 1 << jobIndex;

                person = people.get(i + 1);
                person.index = i;
                person.name = LADIES_NAMES.get(nameIndex);
                person.lastName = LAST_NAMES.get(lastNameIndex);
                person.job = LADIES_JOBS.get(jobIndex);
                person.type = MRS;
            }

            assignSidesPerson(people);

            // restrictions check

            success = true;
            for (byte i = 0; i < RESTRICTIONS.size(); i++) {
                if (!RESTRICTIONS.get(i).test(people)) {
                    success = false;
                    break;
                }
            }

            t2 = System.nanoTime();
            if (Objects.nonNull(timeResults)) {
                timeResults.register(Math.toIntExact(t2 - t1));
            }
        } while (!success);

        if (Objects.nonNull(timeResults)) {
            timeResults.close();
        }

        System.out.printf("\rAttempt: %,d%n", attempt);
    }

    /**
     * Finds a valid solution for the Riddle using Brute-Force.
     *
     * <p>The result will be stored in the {@link Person} instances that are
     * contained in the specified {@link List}. The list also will be sorted
     * based on the next format:<pre>
     *    1 Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs.
     * </pre>
     *
     * <p>As you can see, in the clockwise direction.
     *
     * <p>If a certain amount of attempts is made, the {@link Random} instance
     * will be discarded and another instance will be generated.
     *
     * @param <T>    type of {@link Person}
     * @param people list with the {@link Person} instances to write the results
     * @param random instance to generate pseudo-random indexes
     * @throws NullPointerException     if either the people list or random are {@code null}
     * @throws IllegalArgumentException if the list does not have a size of {@code 8}
     * @apiNote This is an overload of {@link #solve(List, TimeResults, Random)} without time cache.
     */
    public <T extends Person> void solve(final @NonNull List<T> people, final @NonNull Random random) {
        solve(people, null, random);
    }

    // predicate util

    /**
     * Determines whether the specified list of {@link Person} does
     * accomplish the restriction of: <i>A Mr. is not next to a Mrs.
     * with the same last-name.</i>
     *
     * <p>The list must follow the rules of:<pre>
     *     1 Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs.
     * </pre>
     *
     * @param people list of people to check
     * @param <T>    type of {@link Person}
     * @return <tt>true</tt> if the list does not violate the restriction, <tt>false</tt> otherwise
     * @throws IllegalArgumentException if the list does not have a size of {@code 8}
     */
    private static <T extends Person> boolean doesNotHavePartnersTogether(final @NonNull List<T> people) {
        checkArgument(people.size() == 8, "Invalid people list");

        for (byte i = 0, n = 8; i < n; i += 2) {
            final T mr = people.get(i);

            if (mr.lastName == mr.left.lastName || mr.lastName == mr.right.lastName) {
                return true;
            }
        }
        return false;
    }

    // update util

    /**
     * Updates the person a-side of each {@link Person} in the specified {@link List}.
     *
     * <p>The list must follow the rules of:<pre>
     *     1 Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs.
     * </pre>
     *
     * @param people list of people to update the person on their side
     * @param <T>    type of {@link Person}
     * @throws IllegalArgumentException if the list does not have a size of {@code 8}
     */
    private static <T extends Person> void assignSidesPerson(final @NonNull List<T> people) {
        checkArgument(people.size() == 8, "Invalid people list");

        for (byte i = 0, n = 8; i < n; ++i) {
            final T person = people.get(i);

            person.left = people.get(i == n - 1 ? 0 : i + 1);
            person.right = people.get(i == 0 ? n - 1 : i - 1);
            person.front = people.get(i > 3 ? (i + 4) % n : i + 4); // round trip if i > 3
        }
    }

    // filter util

    /**
     * Filters from the specified list, the person with the specified
     * {@link Name} and returns the reference of that person.
     *
     * <p>The list must follow the rules of:<pre>
     *     1 Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs.
     * </pre>
     *
     * @param people list of {@link Person} to filter and search
     * @param name   of the person to look for
     * @param <T>    type of {@link Person}
     * @return the {@link Person} with the specified name
     * @throws IllegalArgumentException if the list does not have a size of {@code 8}
     * @throws IllegalArgumentException if the list does not have a person with that name
     */
    @NonNull
    private static <T extends Person> T filterByName(final @NonNull List<T> people, final @NonNull Name name) {
        checkArgument(people.size() == 8, "Invalid people list");

        for (byte i = 0, n = 8; i < n; ++i) {
            final T person = people.get(i);

            if (person.name == name) {
                return person;
            }
        }
        throw new IllegalArgumentException("No person with name " + name);
    }

    /**
     * Filters from the specified list, the person with the specified
     * {@link LastName} and the specified {@link Type} and returns the
     * reference of that person.
     *
     * <p>Since a {@link Type#MRS} and {@link Type#MR} can have the same
     * {@link LastName}, the type is required to filter the correct search.
     *
     * <p>The list must follow the rules of:<pre>
     *     1 Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs. -> Mr. -> 1 Mrs.
     * </pre>
     *
     * @param people   list of {@link Person} to filter and search
     * @param lastName of the person to look for
     * @param type     of the person to look for
     * @param <T>      type of {@link Person}
     * @return the {@link Person} with the specified last name and type
     * @throws IllegalArgumentException if the list does not have a size of {@code 8}
     */
    @NonNull
    private static <T extends Person> T filterByLastName(final @NonNull List<T> people, final @NonNull LastName lastName, final @NonNull TableRiddle.Type type) {
        checkArgument(people.size() == 8, "Invalid people list");

        for (byte i = (byte) (type == MR ? 0 : 1), n = 8; i < n; i += 2) {
            final T person = people.get(i);

            if (person.lastName == lastName) {
                return person;
            }
        }
        throw new IllegalArgumentException("No " + type + " with last name " + lastName);
    }

    /**
     * Record to track the information of a single {@link Person} in the riddle.
     *
     * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
     * @since 1.6
     */
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person {

        public int index;

        public Name name;
        public LastName lastName;
        public Job job;
        public Type type;

        @ToString.Exclude // cyclic toString
        public Person left;
        @ToString.Exclude // cyclic toString
        public Person right;
        @ToString.Exclude // cyclic toString
        public Person front;
    }

    /**
     * Enum with the possible {@link Name} of a {@link Person} in the riddle.
     *
     * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
     * @since 1.6
     */
    @RequiredArgsConstructor
    public enum Name {

        // sirs' names

        ALBERTO("Alberto"),
        AMBROSIO("Ambrosio"),
        ALFONSO("Alfonso"),
        ALFREDO("Alfredo"),

        // ladies' names

        ANGELA("Angela"),
        AURELIA("Aurelia"),
        ALICIA("Alicia"),
        ANALIA("Analía");

        /**
         * {@link #toString()} value.
         */
        @NonNull
        private final String toString;

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return toString;
        }
    }

    /**
     * Enum with the possible {@link Job} of a {@link Person} in the riddle.
     *
     * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
     * @since 1.6
     */
    @RequiredArgsConstructor
    public enum Job {
        // sirs' jobs

        ACUPUNCTURIST("Acupuntor"),
        LAWYER("Abogado"),
        POSTMASTER("Administrador"), // to god-damn long
        CUSTOMS_OFFICIAL("Aduanero"),

        // ladies' jobs

        ACTRESS("Actriz"),
        WATERCOLORIST("Acuarelista"),
        WRITER("Escritora"),
        BEEKEEPER("Apicultora");

        /**
         * {@link #toString()} value.
         */
        @NonNull
        private final String toString;

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return toString;
        }
    }

    /**
     * Enum with the possible {@link LastName} of a {@link Person} in the riddle.
     *
     * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
     * @since 1.6
     */
    @RequiredArgsConstructor
    public enum LastName {

        // 1 Mr. <-> 1 Mrs.

        ACALA("Acalá"),
        GOMEZ("Gómez"),
        CASTANIO("Castaño"),
        MARTINEZ("Martínez");

        /**
         * {@link #toString()} value.
         */
        @NonNull
        private final String toString;

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return toString;
        }
    }

    /**
     * Whether is Mr. or Mrs.
     *
     * @author <a href="mailto:masterchack92@hotmail.com">Jiizuz</a>
     * @since 1.6
     */
    public enum Type {
        MRS, MR
    }
}
