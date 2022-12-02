package com.example.broc.presentation.f_dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.broc.R
import com.example.broc.presentation.f_home.HomeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class InviteCancelDialogFragment : DialogFragment() {
    private val viewModel: HomeViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext()).setTitle("Cancel Invite")
            .setMessage("Are you sure you want to cancel your invitation?")
            .setNegativeButton("No") { _, _ ->
            }.setPositiveButton("yes") { _, _ ->
                viewModel.cancelInvite()
            }.create()
}