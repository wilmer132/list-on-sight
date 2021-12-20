package edu.stanford.listonsight.data

import android.content.res.Resources

/*Return initial list sample*/
fun userListSample(resources: Resources): List<ListItem> {
    return listOf(
        ListItem(
            id = 1,
            title = "Complete List App",
            description = "Implement first app by myself",
            complete = false
        ),
        ListItem(
            id = 2,
            title = "Visit NYC with gf",
            description = "Bring gf to hometown to meet family",
            complete = true
        )
    )
}