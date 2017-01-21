@org.hibernate.annotations.FetchProfiles({
    @FetchProfile(
    	name = Item.PROFILE_JOIN_SELLER,
    	fetchOverrides = @FetchProfile.FetchOverride(
    		entity = Item.class,
    		association = "seller",
    		mode = FetchMode.JOIN
    	)
    ),

    @FetchProfile(name = Item.PROFILE_JOIN_BIDS,
        fetchOverrides = @FetchProfile.FetchOverride(
            entity = Item.class,
            association = "bid",
            mode = FetchMode.JOIN
        ))
})

package fetch;

import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchMode;
