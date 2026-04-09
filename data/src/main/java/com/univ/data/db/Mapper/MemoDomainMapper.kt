package com.univ.data.db.Mapper

import com.univ.data.model.Memo
import com.univ.domain.model.MemoData

fun Memo.toDomain(): MemoData = MemoData(time = ID, memo = memo)
fun MemoData.toData(): Memo = Memo(ID = time, memo = memo)