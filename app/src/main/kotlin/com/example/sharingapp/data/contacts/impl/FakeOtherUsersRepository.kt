package com.example.sharingapp.data.contacts.impl

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import com.example.sharingapp.data.contacts.ContactsRepository
import com.example.sharingapp.model.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FakeOtherUsersRepository : ContactsRepository {
    val _fakeUsersFlow = MutableStateFlow(
        listOf(
            Contact("Jeremiah Josefer", "jj_123", "jj_123@gmail.com",
                description = "Just a guy who likes collecting tambourines",
                idx = getThenIncrementIdx()),

            Contact("Brandy Buns", "BrandyTheBuns", "brandybun@hotmail.com",
                description = "I have so many gardening tools. If anyone wants to borrow some, just hit me up!",
                idx = getThenIncrementIdx()),

            Contact("Marina Kessler", "MarinaQuest", "marina.q@gmail.com",
                description = "I’m always on the lookout for rare puzzle boxes—mechanical ones are my favorite.",
                idx = getThenIncrementIdx()),

            Contact("Leonard Drift", "DriftwoodLeo", "ldrft@yahoo.com",
                description = "I collect pieces of driftwood with unusual shapes. My house looks like a beach museum.",
                idx = getThenIncrementIdx()),

            Contact("Sasha Penn", "PennAndInk", "sasha.penn@outlook.com",
                description = "Vintage fountain pens are my obsession. I restore them whenever I can.",
                idx = getThenIncrementIdx()),

            Contact("Avery Runnels", "AveryRuns", "avery.r@gmail.com",
                description = "I keep a growing collection of retro running shoes. The colors from the 80s are unbeatable.",
                idx = getThenIncrementIdx()),

            Contact("Talia Merrow", "TaliaM", "talia.merrow@live.com",
                description = "Pressed flowers from different national parks fill my notebooks.",
                idx = getThenIncrementIdx()),

            Contact("Devin Coldwater", "BlueDevin", "devin.coldwater@gmail.com",
                description = "I collect old snow globes—especially the ones that shake up glitter instead of snow.",
                idx = getThenIncrementIdx()),

            Contact("Rafael Biscayne", "RafiBay", "rafi.biscayne@protonmail.com",
                description = "Maps are my passion. I’ve got drawers full of historic charts from around the world.",
                idx = getThenIncrementIdx()),

            Contact("Nadia Lorrens", "NadiaL", "nadialorrens@hotmail.com",
                description = "I’ve been collecting antique keys since I was a kid. Each one feels like it belongs to a story.",
                idx = getThenIncrementIdx()),

            Contact("Owen Kilpatrick", "OwenK", "owenkilpatrick@gmail.com",
                description = "I keep shelves of old board games. The more obscure and rule-heavy, the better.",
                idx = getThenIncrementIdx()),

            Contact("Harper Venn", "H_Venn", "harper.venn@outlook.com",
                description = "Metal lunchboxes are my niche. I hunt for limited editions at flea markets.",
                idx = getThenIncrementIdx()),


            )
    )

    override suspend fun getContacts(): Flow<List<Contact>> {
        return _fakeUsersFlow.asStateFlow()
    }

    override suspend fun addContact(contact: Contact): Boolean {
        // TODO: this is correct, but change the other fake repositories to use getThenIncrementIdx here instead of in the ViewModel - we want low coupling!
        val newUser = Contact(contact.displayName,
            contact.username,
            contact.email,
            description = contact.description,
            idx = getThenIncrementIdx())

        _fakeUsersFlow.update {currentList ->
            currentList + newUser // the .update{} lambda implicitly returns this new list
        }
        // must be outside of .update{}, otherwise the flow will not update
        return true
    }

    override suspend fun deleteContact(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    companion object {
        private var nextIdx : Long = 0
        private fun getThenIncrementIdx() : Long {
            val idx = nextIdx
            nextIdx = nextIdx + 1
            return idx
        }
    }
}