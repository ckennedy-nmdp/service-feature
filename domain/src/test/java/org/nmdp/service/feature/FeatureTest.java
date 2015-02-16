/*

    feature-domain  Feature domain.
    Copyright (c) 2014-2015 National Marrow Donor Program (NMDP)

    This library is free software; you can redistribute it and/or modify it
    under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation; either version 3 of the License, or (at
    your option) any later version.

    This library is distributed in the hope that it will be useful, but WITHOUT
    ANY WARRANTY; with out even the implied warranty of MERCHANTABILITY or
    FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
    License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this library;  if not, write to the Free Software Foundation,
    Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA.

    > http://www.gnu.org/licenses/lgpl.html

*/
package org.nmdp.service.feature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for Feature.
 */
public final class FeatureTest {
    private Feature feature;

    @Before
    public void setUp() {
        feature = new Feature("locus", "term", 2, 42L, "ACGT");
    }

    @Test(expected=NullPointerException.class)
    public void testConstructorNullLocus() {
        new Feature(null, "term", 2, 42L, "ACGT");
    }

    @Test(expected=NullPointerException.class)
    public void testConstructorNullTerm() {
        new Feature("locus", null, 2, 42L, "ACGT");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorInvalidRank() {
        new Feature("locus", "term", -1, 42L, "ACGT");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructorInvalidAccession() {
        new Feature("locus", "term", 2, -1L, "ACGT");
    }

    @Test(expected=NullPointerException.class)
    public void testConstructorNullSequence() {
        new Feature("locus", "term", 2, 42L, null);
    }

    @Test
    public void testConstructor() {
        assertNotNull(feature);
    }

    @Test
    public void testFeature() {
        assertEquals("locus", feature.getLocus());
        assertEquals("term", feature.getTerm());
        assertEquals(2, feature.getRank());
        assertEquals(42L, feature.getAccession());
        assertEquals("ACGT", feature.getSequence());
    }

    @Test
    public void testHashCode() {
        assertEquals(feature.hashCode(), new Feature("locus", "term", 2, 42L, "ACGT").hashCode());
    }

    @Test
    public void testEquals() {
        assertFalse(feature.equals(null));
        assertFalse(feature.equals(new Object()));
        assertFalse(feature.equals(new Feature("hocus", "term", 2, 42L, "ACGT")));
        assertFalse(feature.equals(new Feature("locus", "berm", 2, 42L, "ACGT")));
        assertFalse(feature.equals(new Feature("locus", "term", 3, 42L, "ACGT")));
        assertFalse(feature.equals(new Feature("locus", "term", 2, 24L, "ACGT")));
        assertFalse(feature.equals(new Feature("locus", "term", 2, 42L, "ACGG")));
        assertTrue(feature.equals(new Feature("locus", "term", 2, 42L, "ACGT")));
    }
}
